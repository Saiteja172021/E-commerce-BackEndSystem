package com.ecommerce.estore.controller;

import com.ecommerce.estore.model.Product;
import com.ecommerce.estore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    @Autowired
    private ProductRepository productRepo;

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productRepo.save(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }
}
