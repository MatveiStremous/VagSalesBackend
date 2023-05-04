package com.example.vagsalesbackend;

import com.example.vagsalesbackend.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test()
    public void userTableShouldContainAlLeastOneAdmin() {
        int numOfAdmins = userService.findAll().stream()
                .filter(userResponse -> userResponse.getRole().equals("Администратор"))
                .toList()
                .size();
        Assertions.assertTrue(numOfAdmins>0);
    }
}
