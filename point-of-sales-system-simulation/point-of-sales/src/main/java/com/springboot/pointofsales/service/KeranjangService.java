package com.springboot.pointofsales.service;

import com.springboot.pointofsales.entity.Keranjang;
import com.springboot.pointofsales.entity.MasterBarang;
import com.springboot.pointofsales.entity.MasterKategori;
import com.springboot.pointofsales.repository.KeranjangRepository;
import com.springboot.pointofsales.repository.MasterBarangRepository;
import com.springboot.pointofsales.repository.MasterKategoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeranjangService {

    @Autowired
    private KeranjangRepository keranjangRepository;

    @Autowired
    private MasterBarangRepository masterBarangRepository;

    @Autowired
    private MasterKategoriRepository masterKategoriRepository;

    public List<MasterBarang> findAllBarang(){
        List<MasterBarang> masterBarang = masterBarangRepository.findAll();
        return masterBarang;
    }

    public List<MasterBarang> findBarangByCategory(Long id){
        List<MasterBarang> masterBarang =  masterBarangRepository.barangByCategory(id);
        return masterBarang;
    }

    public Keranjang createKeranjang(Keranjang keranjang){
        MasterBarang masterBarang = masterBarangRepository.findById(keranjang.getMasterBarang().getId())
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();

        keranjang.setName(user);
        keranjang.setMasterBarang(masterBarang);
        return keranjangRepository.save(keranjang);
    }
}
