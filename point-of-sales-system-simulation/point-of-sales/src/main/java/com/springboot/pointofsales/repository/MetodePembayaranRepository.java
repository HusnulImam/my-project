package com.springboot.pointofsales.repository;

import com.springboot.pointofsales.entity.MetodePembayaran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MetodePembayaranRepository extends JpaRepository<MetodePembayaran, Long> {
    @Query(value = "select * from point_of_sales.metode_pembayaran where id = :id", nativeQuery = true)
    MetodePembayaran metodefindById(Long id);
}
