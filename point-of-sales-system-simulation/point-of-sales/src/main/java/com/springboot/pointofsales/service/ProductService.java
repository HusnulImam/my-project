package com.springboot.pointofsales.service;

import com.springboot.pointofsales.dto.ProductDTO;
import com.springboot.pointofsales.entity.Product;
import com.springboot.pointofsales.entity.Category;
import com.springboot.pointofsales.repository.ProductRepository;
import com.springboot.pointofsales.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Product inputProduct(ProductDTO productDTO){

        Category category = categoryRepository.findById(productDTO.getCategory_id())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        Product product = new Product();

        product.setCategory(category);
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        productRepository.save(product);
        return product;
    }

    public Product deleteProduct(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));

        productRepository.delete(product);
        return product;
    }

    public Product updateProduct(Long id, ProductDTO productDTO){

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));

        Category category = categoryRepository.findById(productDTO.getCategory_id())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        product.setCategory(category);
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        productRepository.save(product);
        return product;
    }
}
