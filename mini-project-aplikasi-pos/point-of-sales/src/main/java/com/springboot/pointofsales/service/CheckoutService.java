package com.springboot.pointofsales.service;

import com.springboot.pointofsales.entity.Checkout;
import com.springboot.pointofsales.entity.Keranjang;
import com.springboot.pointofsales.entity.MetodePembayaran;
import com.springboot.pointofsales.repository.CheckoutRepository;
import com.springboot.pointofsales.repository.KeranjangRepository;
import com.springboot.pointofsales.repository.MetodePembayaranRepository;
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
    private MetodePembayaranRepository metodePembayaranRepository;

    @Autowired
    private KeranjangRepository keranjangRepository;

    @Transactional
    public Checkout processCheckout(Checkout checkout){
        MetodePembayaran metodePembayaran = metodePembayaranRepository.findById(checkout.getMetodePembayaran().getId())
                .orElseThrow(() -> new IllegalArgumentException("Metode pembayaran tidak ditemukan"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();

        List<Keranjang> keranjangList = keranjangRepository.findByName(user);
        if(keranjangList.isEmpty()){
            throw new IllegalStateException("Keranjang Kosong");
        }

        double grandTotal = 0.0;
        for(Keranjang newKeranjang: keranjangList){
            double totalHarga = newKeranjang.getAmount()*newKeranjang.getMasterBarang().getPrice();
            grandTotal += totalHarga;
        }

        checkout.setMetodePembayaran(metodePembayaran);
        checkout.setName(user);
        checkout.setKeranjangList(keranjangList);
        checkout.setCreatedDate(LocalDateTime.now());
        checkout.setTotalPrice(grandTotal);

        String number = checkout.getNumber();
        if(number == null){
            checkout.setPaymentDate(LocalDateTime.now());
            checkout.setPaymentStatus("Payment success");
        } else {
            checkout.setPaymentStatus("Pending payment");
        }

        keranjangRepository.deleteAll(keranjangList);
        checkout.getKeranjangList().clear();

        checkoutRepository.save(checkout);

        return checkout;
    }
}
