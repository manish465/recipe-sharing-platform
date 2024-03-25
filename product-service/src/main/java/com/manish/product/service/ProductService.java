package com.manish.product.service;

import com.manish.product.dto.ProductRequestDTO;
import com.manish.product.entity.Product;
import com.manish.product.exception.ApplicationException;
import com.manish.product.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public String createProduct(@Valid ProductRequestDTO productRequestDTO){
        log.info("|| createProduct is called from ProductService class ||");

        Product product = Product.builder()
                .name(productRequestDTO.getName())
                .description(productRequestDTO.getDescription())
                .price(productRequestDTO.getPrice())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .userId(productRequestDTO.getUserId())
                .build();

        return "Product Created with Product Id : " + productRepository.save(product).getProductId();
    }

    public Product getProductByProductId(String productId){
        log.info("|| getProductByProductId is called from ProductService class ||");

        Optional<Product> product = productRepository.findById(productId);

        if(product.isEmpty()){
            throw new ApplicationException("No Product Found");
        }

        return product.get();
    }

    public String editProductByProductId(String productId, @Valid ProductRequestDTO productRequestDTO){
        log.info("|| editProductByProductId is called from ProductService class ||");

        Optional<Product> existingProduct = productRepository.findById(productId);

        if(existingProduct.isEmpty()){
            throw new ApplicationException("No Product Found");
        }

        existingProduct.get().setName(productRequestDTO.getName());
        existingProduct.get().setDescription(productRequestDTO.getDescription());
        existingProduct.get().setPrice(productRequestDTO.getPrice());
        existingProduct.get().setUpdatedAt(LocalDateTime.now());
        existingProduct.get().setUserId(productRequestDTO.getUserId());

        return "Product Updated with Product Id : " + productRepository.save(existingProduct.get()).getProductId();
    }

    public String deleteProductByProductId(String productId){
        log.info("|| deleteProductByProductId is called from ProductService class ||");

        Optional<Product> existingProduct = productRepository.findById(productId);

        if(existingProduct.isEmpty()){
            throw new ApplicationException("No Product Found");
        }

        productRepository.deleteById(productId);

        return "Product Delete with Product Id : " + productId;
    }

    public List<Product> getAllProduct(){
        log.info("|| getAllProduct is called from ProductService class ||");

        return productRepository.findAll();
    }

    public String deleteAllProduct(){
        log.info("|| deleteAllProduct is called from ProductService class ||");

        productRepository.deleteAll();

        return "Product Table Cleared";
    }

    public List<Product> getAllProductByUserId(String userId){
        log.info("|| getAllProductByUserId is called from ProductService class ||");

        return productRepository.findAllByUserId(userId);
    }
}
