package com.geezylucas.introductionspringbatch.domain.service;

import com.geezylucas.introductionspringbatch.application.dto.CarDTO;
import com.geezylucas.introductionspringbatch.domain.model.Car;
import org.springframework.batch.item.ItemProcessor;

public class CarItemProcessor implements ItemProcessor<CarDTO, Car> {

    @Override
    public Car process(CarDTO carDto) {
        return Car.builder()
                .colour(carDto.getColour())
                .model(carDto.getModel())
                .fuelType(carDto.getFuelType())
                .registration(carDto.getRegistration())
                .build();
    }
}