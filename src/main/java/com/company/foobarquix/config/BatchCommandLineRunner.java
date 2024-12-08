package com.company.foobarquix.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BatchCommandLineRunner implements CommandLineRunner {

    private final JobLauncher jobLauncher;
    private final Job job;

    public BatchCommandLineRunner(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length < 1) {
            throw new IllegalArgumentException("A file path must be provided as an argument");
        }

        String filePath = args[0];

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("filePath", filePath)
                .toJobParameters();

        JobExecution jobExecution = jobLauncher.run(job, jobParameters);
        System.out.println("Job Status: " + jobExecution.getStatus());
        System.out.println("Job completed with exit status: " + jobExecution.getExitStatus());
    }

}
