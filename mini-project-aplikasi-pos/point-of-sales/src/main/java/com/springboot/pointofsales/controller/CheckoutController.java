package com.springboot.pointofsales.controller;

import com.springboot.pointofsales.entity.Checkout;
import com.springboot.pointofsales.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @PostMapping("/process-checkout")
    public Checkout createCheckout(@RequestBody Checkout checkout){
        return checkoutService.processCheckout(checkout);
    }
}
