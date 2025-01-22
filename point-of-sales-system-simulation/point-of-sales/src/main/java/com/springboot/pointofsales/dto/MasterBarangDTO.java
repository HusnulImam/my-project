package com.springboot.pointofsales.dto;
import lombok.Data;

@Data
public class MasterBarangDTO {

    private String name;
    private Long kategori_id;
    private double price;
}
