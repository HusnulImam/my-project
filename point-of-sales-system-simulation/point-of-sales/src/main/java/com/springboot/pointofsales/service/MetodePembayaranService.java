package com.springboot.pointofsales.service;

import com.springboot.pointofsales.entity.MetodePembayaran;
import com.springboot.pointofsales.repository.MetodePembayaranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetodePembayaranService {

    @Autowired
    private MetodePembayaranRepository metodePembayaranRepository;

    public MetodePembayaran createPaymentMethod(MetodePembayaran metodePembayaran){
        return metodePembayaranRepository.save(metodePembayaran);
    }

    public MetodePembayaran deletePaymentMethod(Long id){
        MetodePembayaran metodePembayaran = metodePembayaranRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        metodePembayaranRepository.delete(metodePembayaran);
        return metodePembayaran;
    }
}
