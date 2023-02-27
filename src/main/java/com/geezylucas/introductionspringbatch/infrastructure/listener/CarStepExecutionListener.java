package com.geezylucas.introductionspringbatch.infrastructure.listener;

import com.geezylucas.introductionspringbatch.application.dto.CarDTO;
import com.geezylucas.introductionspringbatch.domain.model.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.annotation.AfterRead;
import org.springframework.batch.core.annotation.AfterWrite;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CarStepExecutionListener {

    @AfterRead
    public void afterRead(CarDTO item) {
        log.info("ItemReadListener - afterRead: {}", item);
    }

    @AfterWrite
    public void afterWrite(List<? extends Car> items) {
        log.info("ItemWriteListener - afterWrite: {}", items);
    }
}
