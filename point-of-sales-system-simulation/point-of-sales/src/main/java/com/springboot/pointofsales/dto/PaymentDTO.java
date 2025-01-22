package com.springboot.pointofsales.dto;

import lombok.Data;

@Data
public class PaymentDTO {

    private int checkoutId;
    private String number;
    private double totalPrice;
}
