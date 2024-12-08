package com.company.foobarquix.config;

import com.company.foobarquix.model.Row;
import com.company.foobarquix.service.NumberProcessorService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.support.JdbcTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {


    @Bean
    public ItemProcessor<String, Row> processor() {
        return item -> {
            int number = Integer.parseInt(item.trim());
            return new Row(number, NumberProcessorService.process(number));
        };
    }

    @Bean
    @StepScope
    public FlatFileItemReader<String> reader(@Value("#{jobParameters['filePath']}") String filePath) {
        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException("Job parameter 'filePath' is missing or empty!");
        }
        FlatFileItemReader<String> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource(filePath));
        reader.setLineMapper(new PassThroughLineMapper());
        return reader;
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<Row> writer(@Value("#{jobParameters['filePath']}") String filePath) {
        FlatFileItemWriter<Row> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource(filePath + ".out"));
        writer.setLineAggregator(new DelimitedLineAggregator<>() {{
            setDelimiter("\t");
            setFieldExtractor(pair -> new Object[]{
                    pair.getKey(),
                    "\"" + pair.getValue() + "\""
            });
        }});
        return writer;
    }


    @Bean
    public Job job(JobRepository jobRepository,
                   JdbcTransactionManager transactionManager,
                   FlatFileItemReader<String> reader,
                   FlatFileItemWriter<Row> writer) {
        return new JobBuilder("fileProcessingJob", jobRepository)
                .start(new StepBuilder("step1", jobRepository)
                        .<String, Row>chunk(10, transactionManager) // Process in chunks of 10
                        .reader(reader)
                        .processor(processor())
                        .writer(writer)
                        .build())
                .build();
    }
}
