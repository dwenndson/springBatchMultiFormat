package com.udemy.primeiroprojetospringbatch.jobs.arquivoMultiplosJob;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArquivoMultiplosFormatosJobConfig {

    private final JobRepository jobRepository;

    public ArquivoMultiplosFormatosJobConfig(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Bean
    public Job arquivoMultiplosFormatosJob(Step arquivoMultiplosFormatosStep) {
        return new JobBuilder("arquivoMultiplosFormatosJob", jobRepository)
                .start(arquivoMultiplosFormatosStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
