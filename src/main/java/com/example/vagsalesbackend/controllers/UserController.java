package com.example.vagsalesbackend.controllers;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.vagsalesbackend.dto.AuthDTO;
import com.example.vagsalesbackend.dto.RegistrationDTO;
import com.example.vagsalesbackend.dto.UserDTO;
import com.example.vagsalesbackend.models.User;
import com.example.vagsalesbackend.security.JWTUtil;
import com.example.vagsalesbackend.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

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
    public Map<String, String> register(@RequestBody RegistrationDTO registrationDTO) {
        User user = convertToPerson(registrationDTO);
        userService.save(user);

        String token = jwtUtil.generateAccessToken(userService.findByEmail(registrationDTO.getEmail()));
        return Map.of("jwt-token", token);
    }

    @GetMapping("/validateToken")
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String fullToken){
        String token = fullToken.substring(7);
        try {
            jwtUtil.validateToken(token);
        } catch (TokenExpiredException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
        }
        return ResponseEntity.ok("Token is valid");
    }

    @GetMapping("/getUserInfo")
    public ResponseEntity<UserDTO> getUserInfo(@RequestHeader("Authorization") String fullToken){
        String token = fullToken.substring(7);
        String email = jwtUtil.validateToken(token);
        User user = userService.findByEmail(email);
        return ResponseEntity.ok(this.modelMapper.map(user, UserDTO.class));
    }

    private User convertToPerson(RegistrationDTO registrationDTO){
        return this.modelMapper.map(registrationDTO, User.class);
    }
}
