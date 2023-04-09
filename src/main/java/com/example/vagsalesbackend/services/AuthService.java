package com.example.vagsalesbackend.services;

import com.example.vagsalesbackend.models.User;
import com.example.vagsalesbackend.models.enums.Role;
import com.example.vagsalesbackend.util.exceptions.LoginAlreadyInUseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(User user){
        User userFromDB = userService.findByEmail(user.getEmail());
        if(userFromDB==null){
            user.setRole(Role.ROLE_USER);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user);
        }else{
            throw new LoginAlreadyInUseException();
        }
    }
}
