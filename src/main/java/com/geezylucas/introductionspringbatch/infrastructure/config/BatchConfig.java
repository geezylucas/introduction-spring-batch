package com.geezylucas.introductionspringbatch.infrastructure.config;

import com.geezylucas.introductionspringbatch.application.dto.CarDTO;
import com.geezylucas.introductionspringbatch.domain.model.Car;
import com.geezylucas.introductionspringbatch.domain.service.CarItemProcessor;
import com.geezylucas.introductionspringbatch.infrastructure.adapter.repository.CarRepository;
import com.geezylucas.introductionspringbatch.infrastructure.listener.CarJobExecutionListener;
import com.geezylucas.introductionspringbatch.infrastructure.listener.CarStepExecutionListener;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final CarJobExecutionListener carJobExecutionListener;
    private final CarStepExecutionListener carStepExecutionListener;
    @Lazy
    private final CarRepository carRepository;

    @Bean
    public ItemReader<CarDTO> reader() {
        var beanWrapper = new BeanWrapperFieldSetMapper<CarDTO>();
        beanWrapper.setTargetType(CarDTO.class);

        return new FlatFileItemReaderBuilder<CarDTO>()
                .name("carItemReader")
                .resource(new ClassPathResource("sample-data.csv"))
                .linesToSkip(1)
                .delimited()
                .names("registration", "colour", "model", "fuelType")
                .fieldSetMapper(beanWrapper)
                .build();
    }

    @Bean
    public CarItemProcessor processor() {
        return new CarItemProcessor();
    }

    @Bean
    public RepositoryItemWriter<Car> writer() {
        var repositoryWriter = new RepositoryItemWriter<Car>();
        repositoryWriter.setRepository(carRepository);
        repositoryWriter.setMethodName("save");

        return repositoryWriter;
    }

    @Bean
    public Job processJob() {
        return jobBuilderFactory
                .get("createCarJob")
                .incrementer(new RunIdIncrementer())
                .listener(carJobExecutionListener)
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory
                .get("step1").<CarDTO, Car>chunk(2)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .listener(carStepExecutionListener)
                .build();
    }
}
