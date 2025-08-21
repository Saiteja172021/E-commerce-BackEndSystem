package com.ecommerce.estore.service;

import com.ecommerce.estore.model.Product;
import com.ecommerce.estore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product updateProduct(Long id, Product updatedProduct) {
    return productRepo.findById(id)
            .map(existingProduct -> {
                existingProduct.setName(updatedProduct.getName());
                existingProduct.setPrice(updatedProduct.getPrice());
                existingProduct.setDescription(updatedProduct.getDescription());
                // set other fields here
                return productRepo.save(existingProduct); // updates existing product
            })
            .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
}


    public Optional<Product> getProductById(Long id) {
        return productRepo.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepo.save(product);
    }

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }
}
