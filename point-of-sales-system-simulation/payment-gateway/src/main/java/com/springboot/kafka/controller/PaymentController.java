package com.springboot.kafka.controller;

import com.springboot.kafka.dto.PaymentDTO;
import com.springboot.kafka.dto.PaymentResponse;
import com.springboot.kafka.service.KafkaPaymentService;
import com.springboot.kafka.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private KafkaPaymentService kafkaPaymentService;

    //Endpoint untuk costumer melakukan pembayaran
    @GetMapping("/payment-process")
    public String paymentProcess(@RequestParam String number, @RequestParam double totalPrice){
        try {
            ResponseEntity<PaymentResponse> response = paymentService.getPayment(number, totalPrice);
            paymentService.payment(response.getBody());

            return ("Payment success");

        } catch (Exception e) {
            return ("Payment failed");
        }
    }

    //Endpoint untuk payment gateway mengecek status pembayaran
    //Data status pembayaran tersebut otomatis terkirim ke merchant (project POS)
    @GetMapping("/payment-status")
    public PaymentDTO paymentStatus(@RequestParam String number, @RequestParam double totalPrice){

        PaymentDTO paymentDTO = paymentService.notifyMerchant(number,totalPrice);
        kafkaPaymentService.sendMessage(paymentDTO.getCheckoutId(), paymentDTO.getNumber(),
                paymentDTO.getTotalPrice(), paymentDTO.getPaymentStatus(), paymentDTO.getPaymentDate());
        return paymentDTO;
    }
}
