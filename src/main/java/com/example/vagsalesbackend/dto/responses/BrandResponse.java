package com.example.vagsalesbackend.dto.responses;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BrandResponse {
    private Integer id;
    private String name;
}
