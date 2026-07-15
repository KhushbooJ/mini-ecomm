package com.demo.miniecomm.orders.service;

import com.demo.miniecomm.orders.dto.UserDto;
import com.demo.miniecomm.orders.model.User;
import com.demo.miniecomm.orders.repo.UserRepository;
import com.demo.miniecomm.orders.requests.RegisterUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto findUserByName(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with given name"));

        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    public Long registerUser(RegisterUserRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .hashedPassword(passwordEncoder.encode(request.getRawPassword()))
                .build();
        return userRepository.save(user).getId();
    }

}
