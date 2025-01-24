package com.springboot.pointofsales.service;

import com.springboot.pointofsales.entity.ShoppingCart;
import com.springboot.pointofsales.entity.Product;
import com.springboot.pointofsales.repository.ShoppingCartRepository;
import com.springboot.pointofsales.repository.ProductRepository;
import com.springboot.pointofsales.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAllProduct(){
        List<Product> product = productRepository.findAll();
        return product;
    }

    public List<Product> findProductByCategory(Long id){
        List<Product> product =  productRepository.productByCategory(id);
        return product;
    }

    public ShoppingCart inputShoppingCart(ShoppingCart shoppingCart){
        Product product = productRepository.findById(shoppingCart.getProduct().getId())
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();

        shoppingCart.setName(user);
        shoppingCart.setProduct(product);
        return shoppingCartRepository.save(shoppingCart);
    }
}
