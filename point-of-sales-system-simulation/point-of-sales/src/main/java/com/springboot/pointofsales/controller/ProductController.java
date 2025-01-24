package com.springboot.pointofsales.controller;

import com.springboot.pointofsales.dto.ProductDTO;
import com.springboot.pointofsales.entity.Product;
import com.springboot.pointofsales.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class ProductController {

    @Autowired
    private ProductService productService;

    //Endpoint khusus admin untuk meng-input data barang
    @PostMapping("/product")
    public Product inputProduct(@RequestBody ProductDTO productDTO){
        return productService.inputProduct(productDTO);
    }

    //Endpoint khusus admin untuk menghapus data barang
    @DeleteMapping("/product")
    public String deleteProduct(@RequestParam Long id){
        productService.deleteProduct(id);
        return "Item successfully deleted";
    }

    //Endpoint khusus admin untuk mengubah data barang
    @PutMapping("/product")
    public Product updateProduct(@RequestParam Long id, @RequestBody ProductDTO productDTO) {
        return productService.updateProduct(id, productDTO);
    }
}
