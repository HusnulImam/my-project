package com.springboot.pointofsales.service;

import com.springboot.pointofsales.entity.PaymentMethod;
import com.springboot.pointofsales.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    public PaymentMethod createPaymentMethod(PaymentMethod paymentMethod){
        return paymentMethodRepository.save(paymentMethod);
    }

    public PaymentMethod deletePaymentMethod(Long id){
        PaymentMethod paymentMethod = paymentMethodRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        paymentMethodRepository.delete(paymentMethod);
        return paymentMethod;
    }
}
