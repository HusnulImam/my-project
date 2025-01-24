package com.springboot.pointofsales.repository;

import com.springboot.pointofsales.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select t from Product t where t.category.id = :id")
    List<Product> productByCategory(Long id);
}
