package com.springboot.pointofsales.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Checkout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "metodePembayaran_id", referencedColumnName = "id")
    private MetodePembayaran metodePembayaran;
    private String number;
    private String name;
    private String paymentStatus;
    private LocalDateTime createdDate;
    private LocalDateTime paymentDate;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "checkout_id")
    private List<Keranjang> keranjangList;

    private double totalPrice;
}
