package com.example.vagsalesbackend.controllers;

import com.example.vagsalesbackend.dto.requests.*;
import com.example.vagsalesbackend.models.User;
import com.example.vagsalesbackend.security.JWTUtil;
import com.example.vagsalesbackend.services.AuthService;
import com.example.vagsalesbackend.services.UserService;
import com.example.vagsalesbackend.util.exceptions.BusinessException;
import com.example.vagsalesbackend.util.exceptions.ErrorResponse;
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
public class AuthController {
    private final AuthService authService;
    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Autowired
    public AuthController(AuthService authService, JWTUtil jwtUtil, ModelMapper modelMapper, AuthenticationManager authenticationManager, UserService userService) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthDTO authDTO) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getPassword());

        authenticationManager.authenticate(authToken);

        String token = jwtUtil.generateAccessToken(userService.findByEmail(authDTO.getEmail()));
        return ResponseEntity.ok(Map.of("jwt-token", token));
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException e){
        throw new BusinessException(HttpStatus.BAD_REQUEST, "Неверно введены логин или пароль");
    }

    @PostMapping("/registration")
    public ResponseEntity<String> register(@RequestBody RegistrationDTO registrationDTO) {
        User user = convertToPerson(registrationDTO);
        authService.register(user);

        return ResponseEntity.ok("Регистрация прошла успешно");
    }

    @GetMapping("/validateToken")
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String fullToken){
        String token = fullToken.substring(7);
        jwtUtil.validateToken(token);
        return ResponseEntity.ok("Токен валиден");
    }

    @GetMapping("/getUserInfo")
    public ResponseEntity<UserDTO> getUserInfo(@RequestHeader("Authorization") String fullToken){
        String token = fullToken.substring(7);
        jwtUtil.validateToken(token);
        String email = jwtUtil.getUserNameFromToken(token);
        User user = userService.findByEmail(email);
        return ResponseEntity.ok(this.modelMapper.map(user, UserDTO.class));
    }

    @PutMapping("/changepassword")
    public ResponseEntity<String> changePassword(@RequestHeader("Authorization") String fullToken, @RequestBody ChangePasswordDTO changePasswordDTO){
        String token = fullToken.substring(7);
        User user = userService.findByEmail(jwtUtil.getUserNameFromToken(token));
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getEmail(), changePasswordDTO.getOldPassword());
        try {
            authenticationManager.authenticate(authenticationToken);
        }catch (BadCredentialsException e){
            throw new BusinessException(HttpStatus.CONFLICT, "Неверно введён старый пароль.");
        }
        userService.changePassword(user, changePasswordDTO);
        return ResponseEntity.ok("Пароль успешно сменён");
    }

    @PutMapping("/changeinfo")
    public ResponseEntity<String> changePassword(@RequestBody ChangeUserInfoDTO changePasswordDTO){
        userService.changeUserInfo(changePasswordDTO);
        return ResponseEntity.ok("Информация успешно изменена.");
    }

    private User convertToPerson(RegistrationDTO registrationDTO){
        return this.modelMapper.map(registrationDTO, User.class);
    }
}
