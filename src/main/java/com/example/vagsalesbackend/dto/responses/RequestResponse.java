package com.example.vagsalesbackend.dto.responses;

import lombok.*;

import java.time.LocalDate;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestResponse {
    private Integer id;

    private String status;

    private String name;

    private String email;

    private String phone;

    private Integer carId;

    private LocalDate date;
}
