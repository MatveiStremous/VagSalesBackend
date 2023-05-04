package com.example.vagsalesbackend;

import com.example.vagsalesbackend.models.User;
import com.example.vagsalesbackend.services.AuthService;
import com.example.vagsalesbackend.util.exceptions.BusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthServiceTest {
    @Autowired
    AuthService authService;

    @Test
    public void authServiceShouldThrowBusinessExceptionWhenRegisterUserWithBusyMail() {
        Assertions.assertThrows(BusinessException.class, ()->
                authService.register(User.builder().email("Matthew@gmail.com").password("admin").build())
        );
    }
}
