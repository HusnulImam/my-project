package com.springboot.pointofsales.controller;

import com.springboot.pointofsales.dto.UserAuthRequest;
import com.springboot.pointofsales.dto.TokenResponse;
import com.springboot.pointofsales.dto.UserRegisterRequest;
import com.springboot.pointofsales.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserService userService;

    @PostMapping("/user-register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        try {
            userService.userRegister(userRegisterRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/user-authenticate")
    public ResponseEntity<String> userAuthenticate(@RequestBody UserAuthRequest userAuthRequest) {
        String token = userService.userAuthenticate(userAuthRequest.getEmail(), userAuthRequest.getPassword());
        return ResponseEntity.ok(token); // Mengembalikan token sebagai response
    }
}
