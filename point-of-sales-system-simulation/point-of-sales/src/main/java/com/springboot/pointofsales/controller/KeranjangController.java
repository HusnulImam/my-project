package com.springboot.pointofsales.controller;

import com.springboot.pointofsales.entity.Keranjang;
import com.springboot.pointofsales.entity.MasterBarang;
import com.springboot.pointofsales.service.KeranjangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class KeranjangController {

    @Autowired
    private KeranjangService keranjangService;

    //Endpoint untuk costumer melihat semua data barang yang bisa dibeli
    @GetMapping("/keranjang")
    public List<MasterBarang> findAllBarang(){
        return keranjangService.findAllBarang();
    }

    //Endpoint untuk costumer melihat data barang berdasarkan kategori
    @GetMapping("/keranjang/{id}")
    public List<MasterBarang> findBarangByCategory(@PathVariable Long id){
        return keranjangService.findBarangByCategory(id);
    }

    //Endpoint untuk costumer membuat keranjang belanja
    @PostMapping("/keranjang")
    public Keranjang createKeranjang(@RequestBody Keranjang keranjang){
        return keranjangService.createKeranjang(keranjang);
    }
}
