package com.springboot.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class KafkaPaymentService {

    private static final String TOPIC = "pos_topic";

    private static final Logger log = LoggerFactory.getLogger(KafkaPaymentService.class);

    @Autowired
    private KafkaTemplate <String, Object> kafkaTemplate;

    public void sendMessage(int checkoutId, String number, double totalPrice, String paymentStatus, String paymentDate) {
        Map<String, Object> prop = new HashMap<>();
        prop.put("checkoutId", checkoutId);
        prop.put("number", number);
        prop.put("totalPrice", totalPrice);
        prop.put("paymentStatus", paymentStatus);
        prop.put("paymentDate", paymentDate);
        kafkaTemplate.send(TOPIC, prop);
        log.info("Message sent: {}", prop);
    }

}
