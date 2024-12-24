package com.springboot.pointofsales.controller;

import com.springboot.pointofsales.dto.MasterBarangDTO;
import com.springboot.pointofsales.entity.MasterBarang;
import com.springboot.pointofsales.service.MasterBarangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/")
public class MasterBarangController {

    @Autowired
    private MasterBarangService masterBarangService;

    @PostMapping("/master-barang")
    public MasterBarang createBarang(@RequestBody MasterBarangDTO masterBarangDTO){
        return masterBarangService.createBarang(masterBarangDTO);
    }

    @DeleteMapping("/master-barang")
    public MasterBarang deleteBarang(@RequestParam Long id){
        return masterBarangService.deleteBarang(id);
    }

    @PutMapping("/master-barang")
    public MasterBarang updateBarang(@RequestParam Long id, @RequestBody MasterBarangDTO masterBarangDTO) {
        return masterBarangService.updateBarang(id, masterBarangDTO);
    }
}
