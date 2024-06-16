package com.udemy.primeiroprojetospringbatch.jobs.primeiroJob.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
public class ImprimeOlaStepConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    public ImprimeOlaStepConfig(JobRepository jobRepository, PlatformTransactionManager  platformTransactionManager) {
        this.jobRepository = jobRepository;
        this.platformTransactionManager = platformTransactionManager;
    }

    @Bean
    public Step imprimeOlaStep(Tasklet imprimeOlaTasklet) {
        return new StepBuilder("imprimeOlaJobStep", jobRepository)
                .tasklet(imprimeOlaTasklet, platformTransactionManager)
                .build();
    }

    @Bean
    public Step imprimeParImparStep(){
        return new StepBuilder("imprimeParImparStep", jobRepository)
                .<Integer, String>chunk(10, platformTransactionManager)
                .reader(contarAteDezReader())
                .processor(parOuImparProcessor())
                .writer(imprimeWriter())
                .build();
    }


    public IteratorItemReader<Integer> contarAteDezReader() {
        List<Integer> numerosDeUmAteDez = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        return new IteratorItemReader<>(numerosDeUmAteDez.iterator());
    }

    public FunctionItemProcessor<Integer, String> parOuImparProcessor() {
        return new FunctionItemProcessor<>(item -> item % 2 == 0 ? String.format("Item %s é Par", item) :
                String.format("Item %s é Impar", item));
    }

    public ItemWriter<String> imprimeWriter() {
        return itens -> itens.forEach(System.out::println);
    }

    @Bean
    @StepScope
    public Tasklet imprimeOlaTasklet(@Value("#{jobParameters['nome']}") String nome) {
        return (contribution, chunkContext) -> {
            System.out.printf("Olá, %s!%n", nome);
            return RepeatStatus.FINISHED;
        };
    }
}
