package com.demo.miniecomm.orders.controller;

import com.demo.miniecomm.orders.dto.UserDto;
import com.demo.miniecomm.orders.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
}
