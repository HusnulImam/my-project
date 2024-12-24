package com.springboot.pointofsales.controller;

import com.springboot.pointofsales.entity.Keranjang;
import com.springboot.pointofsales.service.KeranjangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class KeranjangController {

    @Autowired
    private KeranjangService keranjangService;

    @PostMapping("/create-keranjang")
    public Keranjang createKeranjang(@RequestBody Keranjang keranjang){
        return keranjangService.createKeranjang(keranjang);
    }
}
