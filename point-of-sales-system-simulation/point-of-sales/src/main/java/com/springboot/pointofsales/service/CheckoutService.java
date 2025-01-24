package com.springboot.pointofsales.service;

import com.springboot.pointofsales.entity.Checkout;
import com.springboot.pointofsales.entity.ShoppingCart;
import com.springboot.pointofsales.entity.PaymentMethod;
import com.springboot.pointofsales.repository.CheckoutRepository;
import com.springboot.pointofsales.repository.ShoppingCartRepository;
import com.springboot.pointofsales.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CheckoutService {

    @Autowired
    private CheckoutRepository checkoutRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Transactional
    public Checkout processCheckout(Checkout checkout){
        PaymentMethod paymentMethod = paymentMethodRepository.findById(checkout.getPaymentMethod().getId())
                .orElseThrow(() -> new IllegalArgumentException("Payment method not found"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();

        List<ShoppingCart> shoppingCartList = shoppingCartRepository.findByName(user);
        if(shoppingCartList.isEmpty()){
            throw new IllegalStateException("ShoppingCart is empty");
        }

        double grandTotal = 0.0;
        for(ShoppingCart newShoppingCart : shoppingCartList){
            double totalHarga = newShoppingCart.getAmount()* newShoppingCart.getProduct().getPrice();
            grandTotal += totalHarga;
        }

        checkout.setPaymentMethod(paymentMethod);
        checkout.setName(user);
        checkout.setShopppingCartList(shoppingCartList);
        checkout.setCreatedDate(LocalDateTime.now());
        checkout.setTotalPrice(grandTotal);

        //metode id = 3 merupakan metode cash
        if(checkout.getPaymentMethod().getId() == 3){
            checkout.setNumber(null);
            checkout.setPaymentDate(LocalDateTime.now());
            checkout.setPaymentStatus("Payment success");
        } else {
            // Jika metode pembayaran bukan cash, field number wajib diisi
            String number = checkout.getNumber();
            if (number == null || number.isEmpty()) {
                throw new IllegalArgumentException("Number is required");
            }
            checkout.setPaymentStatus("Pending payment");
        }

        shoppingCartRepository.deleteAll(shoppingCartList);
        checkout.getShopppingCartList().clear();

        checkoutRepository.save(checkout);

        return checkout;
    }
}
