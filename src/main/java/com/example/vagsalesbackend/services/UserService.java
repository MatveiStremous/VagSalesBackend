package com.example.vagsalesbackend.services;

import com.example.vagsalesbackend.models.enums.Role;
import com.example.vagsalesbackend.models.User;
import com.example.vagsalesbackend.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(int id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElse(null);
    }

    public User findByEmail(String email) {
        Optional<User> foundUser = userRepository.findByEmail(email);
        return foundUser.orElse(null);
    }

    @Transactional
    public void save(User person) {
        person.setRole(Role.ROLE_USER);
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        userRepository.save(person);
    }

    @Transactional
    public void update(int id, User updatedUser) {
        updatedUser.setId(id);
        userRepository.save(updatedUser);
    }
}
