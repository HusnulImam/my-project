package com.springboot.pointofsales.service;

import com.springboot.pointofsales.dto.UserAuthRequest;
import com.springboot.pointofsales.dto.TokenResponse;
import com.springboot.pointofsales.dto.UserDetailsDTO;
import com.springboot.pointofsales.dto.UserRegisterRequest;
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
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    // Method untuk mendaftarkan user baru
    public User userRegister(UserRegisterRequest userRegisterRequest) {
        // Mengecek apakah email sudah digunakan
        if (userRepository.existsByEmail(userRegisterRequest.getEmail())) {
            throw new IllegalArgumentException("Email already taken");
        }

        // Membuat User baru dan mengenkripsi password
        User user = new User();
        user.setEmail(userRegisterRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        user.setName(userRegisterRequest.getEmail());
        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    // Method untuk login user dan menghasilkan JWT token
    public String userAuthenticate(String email, String password) {
        // Melakukan otentikasi menggunakan AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        // Mengambil userDetails berdasarkan email
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        // Mengecek apakah user berhasil diotentikasi
        if (authentication.isAuthenticated()) {
            // Menghasilkan JWT token untuk user
            return jwtService.generateToken(userDetails, "ROLE_USER");
        } else {
            throw new BadCredentialsException("Invalid email or password");
        }
    }
}
