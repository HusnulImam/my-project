package com.springboot.pointofsales.service;

import com.springboot.pointofsales.dto.MasterBarangDTO;
import com.springboot.pointofsales.entity.MasterBarang;
import com.springboot.pointofsales.entity.MasterKategori;
import com.springboot.pointofsales.repository.MasterBarangRepository;
import com.springboot.pointofsales.repository.MasterKategoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterBarangService {

    @Autowired
    private MasterBarangRepository masterBarangRepository;

    @Autowired
    private MasterKategoriRepository masterKategoriRepository;

    public MasterBarang createBarang(MasterBarangDTO masterBarangDTO){

        MasterKategori masterKategori = masterKategoriRepository.findById(masterBarangDTO.getKategori_id())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        MasterBarang masterBarang = new MasterBarang();

        masterBarang.setMasterKategori(masterKategori);
        masterBarang.setName(masterBarangDTO.getName());
        masterBarang.setPrice(masterBarangDTO.getPrice());
        masterBarangRepository.save(masterBarang);
        return masterBarang;
    }

    public MasterBarang deleteBarang(Long id){
        MasterBarang masterBarang = masterBarangRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));

        masterBarangRepository.delete(masterBarang);
        return masterBarang;
    }

    public MasterBarang updateBarang(Long id, MasterBarangDTO masterBarangDTO){

        MasterBarang masterBarang= masterBarangRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));

        MasterKategori masterKategori = masterKategoriRepository.findById(masterBarangDTO.getKategori_id())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        masterBarang.setMasterKategori(masterKategori);
        masterBarang.setName(masterBarangDTO.getName());
        masterBarang.setPrice(masterBarangDTO.getPrice());
        masterBarangRepository.save(masterBarang);
        return masterBarang;
    }
}
