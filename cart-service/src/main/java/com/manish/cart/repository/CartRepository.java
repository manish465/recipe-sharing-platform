package com.manish.cart.repository;

import com.manish.cart.entity.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CartRepository extends MongoRepository<Cart, String> {
    List<Cart> findAllByUserId(String userId);
    void deleteAllByUserId(String userId);
}
