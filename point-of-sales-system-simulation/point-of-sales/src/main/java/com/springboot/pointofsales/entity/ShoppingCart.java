package com.springboot.pointofsales.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "shopping_cart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    private String name;
    private int amount;
}
