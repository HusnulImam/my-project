package com.springboot.pointofsales.controller;

import com.springboot.pointofsales.entity.ShoppingCart;
import com.springboot.pointofsales.entity.Product;
import com.springboot.pointofsales.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    //Endpoint untuk costumer melihat semua data barang yang bisa dibeli
    @GetMapping("/shopping-cart")
    public List<Product> findAllProduct(){
        return shoppingCartService.findAllProduct();
    }

    //Endpoint untuk costumer melihat data barang berdasarkan kategori
    @GetMapping("/shopping-cart/{id}")
    public List<Product> findProductByCategory(@PathVariable Long id){
        return shoppingCartService.findProductByCategory(id);
    }

    //Endpoint untuk costumer membuat shoppingCart belanja
    @PostMapping("/shopping-cart")
    public ShoppingCart inputShoppingCart(@RequestBody ShoppingCart shoppingCart){
        return shoppingCartService.inputShoppingCart(shoppingCart);
    }
}
