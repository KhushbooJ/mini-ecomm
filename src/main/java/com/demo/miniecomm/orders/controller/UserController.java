package com.demo.miniecomm.orders.controller;

import com.demo.miniecomm.orders.dto.UserDto;
import com.demo.miniecomm.orders.requests.RegisterUserRequest;
import com.demo.miniecomm.orders.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> findUserByName(@PathVariable final String username) {
        UserDto user = userService.findUserByName(username);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@Valid @RequestBody final RegisterUserRequest request) {
        try {
            return ResponseEntity.ok(userService.registerUser(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder(e,HttpStatus.BAD_REQUEST,e.getMessage()));
        }

    }
}
