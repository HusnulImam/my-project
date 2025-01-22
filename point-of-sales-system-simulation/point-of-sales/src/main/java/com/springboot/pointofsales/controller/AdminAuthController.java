package com.springboot.pointofsales.controller;

import com.springboot.pointofsales.dto.*;
import com.springboot.pointofsales.service.AdminService;
import com.springboot.pointofsales.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AdminAuthController {

    private final AdminService adminService;

    @PostMapping("/admin-register")
    public ResponseEntity<String> registerAdmin(@RequestBody AdminRegisterRequest adminRegisterRequest) {
        try {
            adminService.adminRegister(adminRegisterRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Admin registered successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/admin-authenticate")
    public ResponseEntity<String> adminAuthenticate(@RequestBody AdminAuthRequest adminAuthRequest) {
        String token = adminService.adminAuthenticate(adminAuthRequest.getEmail(), adminAuthRequest.getPassword());
        return ResponseEntity.ok(token); // Mengembalikan token sebagai response
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Hello");
    }
}
