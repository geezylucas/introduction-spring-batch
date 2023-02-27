package com.geezylucas.introductionspringbatch.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarDTO {

    private String registration;
    private String colour;
    private String model;
    private String fuelType;
}
