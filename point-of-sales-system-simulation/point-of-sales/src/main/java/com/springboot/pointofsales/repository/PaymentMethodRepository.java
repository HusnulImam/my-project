package com.springboot.pointofsales.repository;

import com.springboot.pointofsales.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    @Query(value = "select * from point_of_sales.metode_pembayaran where id = :id", nativeQuery = true)
    PaymentMethod metodefindById(Long id);
}
