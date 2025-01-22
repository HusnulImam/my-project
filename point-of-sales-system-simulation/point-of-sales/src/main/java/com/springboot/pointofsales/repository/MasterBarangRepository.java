package com.springboot.pointofsales.repository;

import com.springboot.pointofsales.entity.MasterBarang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterBarangRepository extends JpaRepository<MasterBarang, Long> {
    @Query(value = "select t from MasterBarang t where t.masterKategori.id = :id")
    List<MasterBarang> barangByCategory(Long id);
}
