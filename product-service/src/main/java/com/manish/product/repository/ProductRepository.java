package com.manish.product.repository;

import com.manish.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findAllByUserId(String userId);
}
