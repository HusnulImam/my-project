package com.springboot.kafka.dto;

import lombok.Data;

@Data
public class PaymentResponse {

    private int checkoutId;
    private String number;
    private double totalPrice;
}
