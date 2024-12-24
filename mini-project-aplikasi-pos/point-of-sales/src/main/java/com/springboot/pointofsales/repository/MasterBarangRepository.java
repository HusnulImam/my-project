package com.springboot.pointofsales.repository;

import com.springboot.pointofsales.entity.MasterBarang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterBarangRepository extends JpaRepository<MasterBarang, Long> {
}
