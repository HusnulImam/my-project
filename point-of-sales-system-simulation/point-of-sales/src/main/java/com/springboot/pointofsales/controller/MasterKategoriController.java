package com.springboot.pointofsales.controller;

import com.springboot.pointofsales.dto.MasterKategoriDTO;
import com.springboot.pointofsales.entity.MasterKategori;
import com.springboot.pointofsales.service.MasterKategoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class MasterKategoriController {

    @Autowired
    private MasterKategoriService masterKategoriService;

    //Endpoint khusus admin untuk meng-input data kategori
    @PostMapping("/master-category")
    public MasterKategori createCategory(@RequestBody MasterKategori masterKategori){
        return masterKategoriService.createCategory(masterKategori);
    }

    //Endpoint khusus admin untuk menghapus data kategori
    @DeleteMapping("/master-category")
    public String deleteCategory(@RequestParam Long id){
        masterKategoriService.deleteCategory(id);
        return "Category successfully deleted";
    }

    //Endpoint khusus admin untuk mengubah data kategori
    @PutMapping("/master-category")
    public MasterKategori updateCategory(
            @RequestParam Long id,
            @RequestBody MasterKategoriDTO masterKategoriDTO
    ){
        return masterKategoriService.updateCategory(id, masterKategoriDTO);
    }
}
