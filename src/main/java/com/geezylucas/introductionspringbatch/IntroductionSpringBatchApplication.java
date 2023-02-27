package com.geezylucas.introductionspringbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class IntroductionSpringBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntroductionSpringBatchApplication.class, args);
    }

}
