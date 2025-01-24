package com.springboot.pointofsales.controller;

import com.springboot.pointofsales.dto.PaymentDTO;
import com.springboot.pointofsales.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    //Endpoint ini tidak perlu di test
    //Endpoint ini akan di panggil oleh project payment gateway
    @GetMapping("/payment-process")
    public ResponseEntity<PaymentDTO> paymentProcess(
            @RequestParam String number,
            @RequestParam double totalPrice
    ) {
        try {
            PaymentDTO paymentDTO= paymentService.paymentByNumberAndTotalPrice(number, totalPrice);
            if (paymentDTO != null) {
                return ResponseEntity.ok(paymentDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
