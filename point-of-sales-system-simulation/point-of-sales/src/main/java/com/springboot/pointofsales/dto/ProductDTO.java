package com.springboot.pointofsales.dto;
import lombok.Data;

@Data
public class ProductDTO {

    private String name;
    private Long category_id;
    private double price;
}
