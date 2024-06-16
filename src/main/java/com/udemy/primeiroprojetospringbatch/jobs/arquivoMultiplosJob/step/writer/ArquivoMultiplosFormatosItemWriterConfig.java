package com.udemy.primeiroprojetospringbatch.jobs.arquivoMultiplosJob.step.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArquivoMultiplosFormatosItemWriterConfig {

    @SuppressWarnings("unchecked")
    @Bean
    public ItemWriter leituraArquivoLarguraFixaWriter() {
        return items -> items.forEach(System.out::println);
    }
}
