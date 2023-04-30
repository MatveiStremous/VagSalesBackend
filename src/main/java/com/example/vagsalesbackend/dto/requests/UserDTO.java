package com.example.vagsalesbackend.dto.requests;

import com.example.vagsalesbackend.models.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {
    private String email;
    private String name;
    private String phone;

    private Role role;
}
