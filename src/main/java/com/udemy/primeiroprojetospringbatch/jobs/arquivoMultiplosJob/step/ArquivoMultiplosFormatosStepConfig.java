package com.udemy.primeiroprojetospringbatch.jobs.arquivoMultiplosJob.step;

import com.udemy.primeiroprojetospringbatch.dominio.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ArquivoMultiplosFormatosStepConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    public ArquivoMultiplosFormatosStepConfig(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        this.jobRepository = jobRepository;
        this.platformTransactionManager = platformTransactionManager;
    }

    @SuppressWarnings("unchecked")
    @Bean
    public Step arquivoMultiplosFormatosStep(FlatFileItemReader leituraArquivoMutiplosReader,
                                              ItemWriter escritaArquivoMutiplosWriter) {
        return new StepBuilder("arquivoMultiplosFormatosStep", jobRepository)
                .<Cliente, Cliente>chunk(1, platformTransactionManager)
                .reader(leituraArquivoMutiplosReader)
                .writer(escritaArquivoMutiplosWriter)
                .build();
    }
}