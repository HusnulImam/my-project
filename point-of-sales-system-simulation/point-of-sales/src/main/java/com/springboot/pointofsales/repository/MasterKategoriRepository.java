package com.springboot.pointofsales.repository;

import com.springboot.pointofsales.entity.MasterKategori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterKategoriRepository extends JpaRepository <MasterKategori, Long> {
}
