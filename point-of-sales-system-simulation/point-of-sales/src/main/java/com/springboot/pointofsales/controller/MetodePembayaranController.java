package com.springboot.pointofsales.controller;

import com.springboot.pointofsales.entity.MetodePembayaran;
import com.springboot.pointofsales.service.MetodePembayaranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class MetodePembayaranController {

    @Autowired
    private MetodePembayaranService metodePembayaranService;

    //Endpoint khusus admin untuk meng-input data metode pembayaran
    //Metode bisa berupa virtual account, cash, Qris, dll
    @PostMapping("/create-metode-pembayaran")
    public MetodePembayaran createMetodePembayaran(@RequestBody MetodePembayaran metodePembayaran){
        return metodePembayaranService.createMetode(metodePembayaran);
    }
}
