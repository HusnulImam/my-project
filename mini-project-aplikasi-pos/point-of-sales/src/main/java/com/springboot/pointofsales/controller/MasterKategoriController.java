package com.springboot.pointofsales.controller;

import com.springboot.pointofsales.entity.MasterKategori;
import com.springboot.pointofsales.service.MasterKategoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class MasterKategoriController {

    @Autowired
    private MasterKategoriService masterKategoriService;

    @PostMapping("/master-category")
    public MasterKategori createCategory(@RequestBody MasterKategori masterKategori){
        return masterKategoriService.createCategory(masterKategori);
    }

    @DeleteMapping("/master-category")
    public MasterKategori deleteCategory(@RequestParam Long id){
        return masterKategoriService.deleteCategory(id);
    }
}
