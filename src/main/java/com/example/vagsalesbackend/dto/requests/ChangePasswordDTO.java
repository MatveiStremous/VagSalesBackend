package com.example.vagsalesbackend.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChangePasswordDTO {
    private String oldPassword;
    private String newPassword;
}
