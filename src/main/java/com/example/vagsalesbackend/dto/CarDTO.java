package com.example.vagsalesbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CarDTO {
    private String bodyType;

    private String fuelType;

    private String transmission;

    private Double engineCapacity;

    private String year;

    private String imageURL;

    private String description;

    private Integer modelId;
}
