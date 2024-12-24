package com.springboot.pointofsales.dto;

import lombok.Data;

@Data
public class PaymentStatus {

    private int checkoutId;
    private String number;
    private double totalPrice;
    private String paymentStatus;
    private String paymentDate;

    @Override
    public String toString() {
        return "PaymentData{" +
                "checkoutId=" + checkoutId +
                ", number=" + number +
                ", paymentDate=" + paymentDate +
                ", paymentStatus=" + paymentStatus +
                ", totalPrice=" + totalPrice + '\'' +
                '}';
    }
}
