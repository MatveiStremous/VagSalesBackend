package com.example.vagsalesbackend.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthDTO {
    private String email;
    private String password;
}
