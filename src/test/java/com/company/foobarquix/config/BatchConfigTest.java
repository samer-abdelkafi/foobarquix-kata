package com.company.foobarquix.config;

import com.company.foobarquix.FoobarquixApplication;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBatchTest
@SpringJUnitConfig(FoobarquixApplication.class)
public class BatchConfigTest {

    @Value("classpath:input-test-file.txt")
    private Resource inputFileResource;

    @Value("classpath:output-file-result.txt")
    private Resource resultFileResource;

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;


    @Test
    public void testJob(@Autowired Job job) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("filePath", inputFileResource.getFile().getAbsolutePath())
                .toJobParameters();
        this.jobLauncherTestUtils.setJob(job);
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());
        String expectedResult = Files.readString(Paths.get(resultFileResource.getURI()));
        String result = Files.readString(Paths.get(inputFileResource.getFile().getAbsolutePath()
                .replace(".txt", "-output.txt")));
        assertEquals(expectedResult, result);
    }
}