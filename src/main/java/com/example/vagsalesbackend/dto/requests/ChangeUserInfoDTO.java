package com.example.vagsalesbackend.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChangeUserInfoDTO {
    private String email;
    private String name;
    private String phone;
}
