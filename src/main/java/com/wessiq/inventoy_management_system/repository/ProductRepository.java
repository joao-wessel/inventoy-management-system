package com.wessiq.inventoy_management_system.repository;

import com.wessiq.inventoy_management_system.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}