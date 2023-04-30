package com.example.vagsalesbackend.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDTO {
    private String email;
    private String password;
    private String name;
    private String phone;
}
