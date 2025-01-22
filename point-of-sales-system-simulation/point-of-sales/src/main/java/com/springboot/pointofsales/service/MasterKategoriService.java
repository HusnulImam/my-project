package com.springboot.pointofsales.service;

import com.springboot.pointofsales.dto.MasterKategoriDTO;
import com.springboot.pointofsales.entity.MasterKategori;
import com.springboot.pointofsales.repository.MasterKategoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterKategoriService {

    @Autowired
    private MasterKategoriRepository masterKategoriRepository;

    public MasterKategori createCategory(MasterKategori masterKategori){
        return masterKategoriRepository.save(masterKategori);
    }

    public MasterKategori deleteCategory(Long id){
        MasterKategori masterKategori = masterKategoriRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        masterKategoriRepository.delete(masterKategori);
        return masterKategori;
    }

    public MasterKategori updateCategory(Long id, MasterKategoriDTO masterKategoriDTO){
        MasterKategori masterKategori = masterKategoriRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Category not Found"));

        masterKategori.setName(masterKategoriDTO.getName());
        masterKategoriRepository.save(masterKategori);
        return masterKategori;
    }
}
