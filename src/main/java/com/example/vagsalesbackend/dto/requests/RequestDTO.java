package com.example.vagsalesbackend.dto.requests;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {
    private String status;

    private String name;

    private String email;

    private String phone;

    private Integer carId;
}
