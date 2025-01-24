package com.springboot.pointofsales.controller;

import com.springboot.pointofsales.entity.PaymentMethod;
import com.springboot.pointofsales.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    //Endpoint khusus admin untuk meng-input data metode pembayaran
    //Metode bisa berupa virtual account, cash, Qris, dll
    @PostMapping("/payment-method")
    public PaymentMethod createPaymentMethod(@RequestBody PaymentMethod paymentMethod){
        return paymentMethodService.createPaymentMethod(paymentMethod);
    }

    @DeleteMapping("/payment-method")
    public String deletePaymentMethod(@RequestParam Long id){
        paymentMethodService.deletePaymentMethod(id);
        return "Payment method successfully deleted";
    }
}
