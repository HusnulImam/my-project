package com.springboot.pointofsales.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "barang")
public class MasterBarang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "kategori_id", referencedColumnName = "id")
    private MasterKategori masterKategori;

    private double price;
}
