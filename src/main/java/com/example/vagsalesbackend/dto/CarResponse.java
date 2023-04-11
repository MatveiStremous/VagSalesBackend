package com.example.vagsalesbackend.dto;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CarResponse {
    private Integer id;

    private String bodyType;

    private String transmission;

    private Double engineCapacity;

    private String year;

    private String imageURL;

    private String modelName;

    private String brandName;
}
