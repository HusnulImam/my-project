package com.springboot.pointofsales.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.pointofsales.dto.PaymentStatus;
import com.springboot.pointofsales.entity.Checkout;
import com.springboot.pointofsales.repository.CheckoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class KafkaConsumerService {

    @Autowired
    private CheckoutRepository checkoutRepository;

    @Transactional
    @KafkaListener(topics = "pos_topic", groupId = "group_id")
    public void consume(String topic, String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            PaymentStatus paymentStatus = objectMapper.readValue(message, PaymentStatus.class);

            String paymentDateString = paymentStatus.getPaymentDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime =LocalDateTime.parse(paymentDateString, formatter);

            Long checkoutId = Long.valueOf(paymentStatus.getCheckoutId());

            Optional<Checkout> checkoutOpt = checkoutRepository.findById(checkoutId);

            //Data checkout akan otomatis ter-update dengan data yang diterima dari payment gateway
            if (checkoutOpt.isPresent()){
                Checkout checkout = checkoutOpt.get();
                checkout.setPaymentStatus(paymentStatus.getPaymentStatus());
                checkout.setPaymentDate(dateTime);
                checkoutRepository.save(checkout);
                System.out.println("Checkout updated: " + checkout);
            } else {
                System.err.println("Checkout with ID " + checkoutId + " not found.");
            }
        } catch (JsonProcessingException e) {
            System.err.println("Failed to process message: " + e.getMessage());
        }
        System.out.println("Message Recieved: " + message);
    }
}
