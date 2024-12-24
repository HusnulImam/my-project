package com.springboot.pointofsales.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "kategori")
public class MasterKategori {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

}
