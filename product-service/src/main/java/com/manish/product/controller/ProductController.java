package com.manish.product.controller;

import com.manish.product.dto.ProductRequestDTO;
import com.manish.product.entity.Product;
import com.manish.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/product")
@SuppressWarnings("unused")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public ResponseEntity<String> home(){
        log.info("|| home is called from ProductController class ||");

        return new ResponseEntity<>("This is Product Service", HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<String> createProduct(@RequestBody ProductRequestDTO productRequestDTO){
        log.info("|| createProduct is called from ProductController class ||");

        return new ResponseEntity<>(productService.createProduct(productRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductByProductId(@PathVariable String productId){
        log.info("|| getProductByProductId is called from ProductController class ||");

        return new ResponseEntity<>(productService.getProductByProductId(productId), HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<String> editProductByProductId(@PathVariable String productId, @RequestBody ProductRequestDTO productRequestDTO){
        log.info("|| editProductByProductId is called from ProductController class ||");

        return new ResponseEntity<>(productService.editProductByProductId(productId, productRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProductByProductId(@PathVariable String productId){
        log.info("|| deleteProductByProductId is called from ProductController class ||");

        return new ResponseEntity<>(productService.deleteProductByProductId(productId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProduct(){
        log.info("|| getAllProduct is called from ProductController class ||");

        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAllProduct(){
        log.info("|| deleteAllProduct is called from ProductController class ||");

        return new ResponseEntity<>(productService.deleteAllProduct(), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Product>> getAllProductByUserId(@PathVariable String userId){
        log.info("|| getAllProductByUserId is called from ProductController class ||");

        return new ResponseEntity<>(productService.getAllProductByUserId(userId), HttpStatus.OK);
    }
}
