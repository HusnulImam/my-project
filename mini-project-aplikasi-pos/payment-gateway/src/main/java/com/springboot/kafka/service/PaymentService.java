package com.springboot.kafka.service;

import com.springboot.kafka.dto.PaymentDTO;
import com.springboot.kafka.dto.PaymentResponse;
import com.springboot.kafka.entity.Payment;
import com.springboot.kafka.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${payment.api.url}")  // URL API VirtualAccount yang bisa diambil dari konfigurasi
    private String paymentApiUrl;

    public void payment(PaymentResponse paymentResponse) {

        Payment payment = new Payment();

        payment.setCheckoutId(paymentResponse.getCheckoutId());
        payment.setNumber(paymentResponse.getNumber());
        payment.setTotalPrice(paymentResponse.getTotalPrice());
        payment.setPaymentStatus("Payment Success");
        payment.setPaymentDate(LocalDateTime.now());
        paymentRepository.save(payment);
    }

    public ResponseEntity<PaymentResponse> getPayment(String number, double totalprice){
        String url = paymentApiUrl + "/api/v1/payment-process" +
                "?number=" + number +
                "&totalPrice=" + totalprice;

        ResponseEntity<PaymentResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                PaymentResponse.class
        );
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return ResponseEntity.ok(response.getBody());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public PaymentDTO notifyMerchant(String number, double totalPrice) {
        Optional<Payment> paymentOpt = paymentRepository.findByNumberAndTotalPrice(number, totalPrice);

        if (paymentOpt.isPresent()) {
            Payment payment = paymentOpt.get();

            LocalDateTime paymentDate = payment.getPaymentDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatPaymentDate = paymentDate.format(formatter);

            PaymentDTO paymentDTO = new PaymentDTO();
            paymentDTO.setCheckoutId(payment.getCheckoutId());
            paymentDTO.setNumber(payment.getNumber());
            paymentDTO.setTotalPrice(payment.getTotalPrice());
            paymentDTO.setPaymentStatus(payment.getPaymentStatus());
            paymentDTO.setPaymentDate(formatPaymentDate);

            return paymentDTO;
        } else {
            return null;
        }
    }

}