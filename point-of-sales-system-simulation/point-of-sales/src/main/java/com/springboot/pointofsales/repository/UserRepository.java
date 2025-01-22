package com.springboot.pointofsales.repository;

import com.springboot.pointofsales.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    // Memeriksa apakah email sudah ada
    boolean existsByEmail(String email);
}

