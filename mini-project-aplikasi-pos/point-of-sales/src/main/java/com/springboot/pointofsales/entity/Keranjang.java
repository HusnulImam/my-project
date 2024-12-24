package com.springboot.pointofsales.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Keranjang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "barang_id", referencedColumnName = "id")
    private MasterBarang masterBarang;
    private String name;
    private int amount;
}
