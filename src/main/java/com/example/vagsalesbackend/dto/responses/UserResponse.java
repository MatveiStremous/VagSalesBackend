package com.example.vagsalesbackend.dto.responses;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Integer id;
    private String email;
    private String name;
    private String phone;
    private String role;
}
