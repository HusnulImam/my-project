package com.springboot.pointofsales.service;

import com.springboot.pointofsales.entity.Keranjang;
import com.springboot.pointofsales.entity.MasterBarang;
import com.springboot.pointofsales.repository.KeranjangRepository;
import com.springboot.pointofsales.repository.MasterBarangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class KeranjangService {

    @Autowired
    private KeranjangRepository keranjangRepository;

    @Autowired
    private MasterBarangRepository masterBarangRepository;

    public Keranjang createKeranjang(Keranjang keranjang){
        MasterBarang masterBarang = masterBarangRepository.findById(keranjang.getMasterBarang().getId())
                .orElseThrow(() -> new RuntimeException("Barang Tidak Tersedia"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();

        keranjang.setName(user);
        keranjang.setMasterBarang(masterBarang);
        return keranjangRepository.save(keranjang);
    }
}
