package com.example.vagsalesbackend.controllers;

import com.example.vagsalesbackend.dto.responses.UserResponse;
import com.example.vagsalesbackend.models.User;
import com.example.vagsalesbackend.models.enums.Role;
import com.example.vagsalesbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserResponse> getAllRequests() {
        return userService.findAll();
    }

    @PutMapping("/user/role/{id}/{role}")
    public ResponseEntity<String> updateUserRoleById(@PathVariable String role, @PathVariable Integer id) {
        User user = userService.findOne(id);
        user.setRole(Role.getByPrefix(role));
        userService.update(id, user);
        return ResponseEntity.ok("Роль успешно обновлена.");
    }
}
