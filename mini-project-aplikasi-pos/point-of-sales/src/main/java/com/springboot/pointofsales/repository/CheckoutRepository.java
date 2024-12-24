package com.springboot.pointofsales.repository;

import com.springboot.pointofsales.entity.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {
    List<Checkout> findByNumberAndTotalPrice(String number, double totalPrice);
}
