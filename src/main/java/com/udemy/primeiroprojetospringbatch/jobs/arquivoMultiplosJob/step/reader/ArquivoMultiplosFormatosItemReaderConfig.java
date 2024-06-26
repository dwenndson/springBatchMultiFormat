package com.udemy.primeiroprojetospringbatch.jobs.arquivoMultiplosJob.step.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ArquivoMultiplosFormatosItemReaderConfig {

    @SuppressWarnings({"rawtypes", "unchecked"})
    @StepScope
    @Bean
    public FlatFileItemReader arquivosMutiplosFormatosItemReader(
            @Value("#{jobParameters['arquivoClientes']}") Resource arquivoClientes,
            LineMapper lineMapper
            ) {
        return new FlatFileItemReaderBuilder()
                .name("arquivosMutiplosFormatosItemReader")
                .resource(arquivoClientes)
                .lineMapper(lineMapper)
                .build();

    }
}
