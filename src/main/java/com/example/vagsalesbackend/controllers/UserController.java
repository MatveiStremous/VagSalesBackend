package com.example.vagsalesbackend.controllers;

import com.example.vagsalesbackend.dto.AuthDTO;
import com.example.vagsalesbackend.dto.UserDTO;
import com.example.vagsalesbackend.models.User;
import com.example.vagsalesbackend.security.JWTUtil;
import com.example.vagsalesbackend.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {
    private final UserService userService;
    private final JWTUtil jwtUtil;

    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService, JWTUtil jwtUtil, ModelMapper modelMapper, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody AuthDTO authDTO) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getPassword());

        try {
            authenticationManager.authenticate(authToken);
        } catch (BadCredentialsException e) {
            return Map.of("message", "Incorrect credentials!");
        }

        String token = jwtUtil.generateAccessToken(userService.findByEmail(authDTO.getEmail()));
        return Map.of("jwt-token", token);
    }

    @PostMapping("/registration")
    public Map<String, String> register(@RequestBody UserDTO userDTO) {
        User user = convertToPerson(userDTO);
        userService.save(user);

        String token = jwtUtil.generateAccessToken(userService.findByEmail(userDTO.getEmail()));
        return Map.of("jwt-token", token);
    }

    private User convertToPerson(UserDTO userDTO){
        return this.modelMapper.map(userDTO, User.class);
    }
}
