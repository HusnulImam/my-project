package com.springboot.pointofsales.service;

import com.springboot.pointofsales.dto.*;
import com.springboot.pointofsales.entity.User;
import com.springboot.pointofsales.jwt.JwtService;
import com.springboot.pointofsales.repository.UserRepository;
import com.springboot.pointofsales.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService; // Bisa disesuaikan dengan AdminDetailsService jika perlu

    // Method untuk mendaftarkan user baru
    public User adminRegister(AdminRegisterRequest adminRegisterRequest) {
        // Mengecek apakah email sudah digunakan
        if (userRepository.existsByEmail(adminRegisterRequest.getEmail())) {
            throw new IllegalArgumentException("Email already taken");
        }

        // Membuat User baru dan mengenkripsi password
        User user = new User();
        user.setEmail(adminRegisterRequest.getEmail());
        user.setPassword(passwordEncoder.encode(adminRegisterRequest.getPassword()));
        user.setName(adminRegisterRequest.getEmail());
        user.setRole(Role.ADMIN);

        return userRepository.save(user);
    }

    // Method untuk login admin dan menghasilkan JWT token
    public String adminAuthenticate(String email, String password) {
        // Melakukan otentikasi menggunakan AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        // Mengambil adminDetails berdasarkan email
        UserDetails adminDetails = userDetailsService.loadUserByUsername(email); // Bisa disesuaikan dengan AdminDetails

        // Mengecek apakah admin berhasil diotentikasi
        if (authentication.isAuthenticated()) {
            // Menghasilkan JWT token untuk admin
            return jwtService.generateToken(adminDetails, "ROLE_ADMIN");
        } else {
            throw new BadCredentialsException("Invalid email or password");
        }
    }
}
