package com.product.ProductFull.repository;

import com.product.ProductFull.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
