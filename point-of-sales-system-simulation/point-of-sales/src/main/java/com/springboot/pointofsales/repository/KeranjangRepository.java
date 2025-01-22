package com.springboot.pointofsales.repository;

import com.springboot.pointofsales.entity.Keranjang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeranjangRepository extends JpaRepository<Keranjang, Long> {
    List<Keranjang> findByName(String name);
}
