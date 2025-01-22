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

    //Endpoint khusus admin untuk meng-input data barang
    @PostMapping("/master-barang")
    public MasterBarang createBarang(@RequestBody MasterBarangDTO masterBarangDTO){
        return masterBarangService.createBarang(masterBarangDTO);
    }

    //Endpoint khusus admin untuk menghapus data barang
    @DeleteMapping("/master-barang")
    public String deleteBarang(@RequestParam Long id){
        masterBarangService.deleteBarang(id);
        return "Item successfully deleted";
    }

    //Endpoint khusus admin untuk mengubah data barang
    @PutMapping("/master-barang")
    public MasterBarang updateBarang(@RequestParam Long id, @RequestBody MasterBarangDTO masterBarangDTO) {
        return masterBarangService.updateBarang(id, masterBarangDTO);
    }
}
