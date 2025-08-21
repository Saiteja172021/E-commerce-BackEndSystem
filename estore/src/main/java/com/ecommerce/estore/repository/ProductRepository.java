package com.ecommerce.estore.repository;

import com.ecommerce.estore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductRepository extends JpaRepository<Product, Long> {
}
