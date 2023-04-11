package com.example.vagsalesbackend.dto;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ModelResponse {
    private Integer id;
    private String name;
    private String description;
    private Integer brandId;
}
