package com.springboot.kafka.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "payment")
public class    Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private int checkoutId;
    private String number;
    private double totalPrice;
    private String paymentStatus;
    private LocalDateTime paymentDate;
}
