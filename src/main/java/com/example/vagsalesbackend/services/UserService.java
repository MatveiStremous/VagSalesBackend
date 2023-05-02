package com.example.vagsalesbackend.services;

import com.example.vagsalesbackend.dto.requests.ChangePasswordDTO;
import com.example.vagsalesbackend.dto.requests.ChangeUserInfoDTO;
import com.example.vagsalesbackend.models.User;
import com.example.vagsalesbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll() {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public User findOne(int id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElse(null);
    }

    public User findByEmail(String email) {
        Optional<User> foundUser = userRepository.findByEmail(email);
        return foundUser.orElse(null);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void update(int id, User updatedUser) {
        updatedUser.setId(id);
        userRepository.save(updatedUser);
    }

    public void changePassword(User user, ChangePasswordDTO changePasswordDTO) {
        user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        update(user.getId(), user);
    }

    public void changeUserInfo(ChangeUserInfoDTO changeUserInfoDTO) {
        User user = findByEmail(changeUserInfoDTO.getEmail());
        user.setName(changeUserInfoDTO.getName());
        user.setPhone(changeUserInfoDTO.getPhone());
        update(user.getId(), user);
    }
}
