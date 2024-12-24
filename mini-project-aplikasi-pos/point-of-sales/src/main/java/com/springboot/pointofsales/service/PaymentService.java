package com.springboot.pointofsales.service;

import com.springboot.pointofsales.dto.PaymentDTO;
import com.springboot.pointofsales.entity.Checkout;
import com.springboot.pointofsales.repository.CheckoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private CheckoutRepository checkoutRepository;


    public PaymentDTO paymentByNumberAndTotalPrice(String number, double totalPrice){
        List<Checkout> checkouts = checkoutRepository.findByNumberAndTotalPrice(number, totalPrice);

        if (checkouts.isEmpty()) {
            return null;
        }

        for (Checkout checkout : checkouts) {
            if ("Pending payment".equals(checkout.getPaymentStatus())) {
                PaymentDTO paymentDTO = new PaymentDTO();

                int checkoutId = Math.toIntExact(checkout.getId());
                paymentDTO.setCheckoutId(checkoutId);
                paymentDTO.setNumber(checkout.getNumber());
                paymentDTO.setTotalPrice(checkout.getTotalPrice());

                return paymentDTO;
            }
        }
        return null;
    }
}
