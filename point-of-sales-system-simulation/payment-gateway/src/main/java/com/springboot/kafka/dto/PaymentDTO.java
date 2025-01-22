package com.springboot.kafka.dto;

import lombok.Data;

@Data
public class PaymentDTO {

    private int checkoutId;
    private String number;
    private double totalPrice;
    private String paymentStatus;
    private String paymentDate;
}
