package com.manish.cart.controller;

import com.manish.cart.dto.CreateCartRequestDTO;
import com.manish.cart.entity.Cart;
import com.manish.cart.entity.Item;
import com.manish.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@Slf4j
@SuppressWarnings("unused")
public class CartController {
    private final CartService cartService;

    @PostMapping("/")
    public ResponseEntity<String> createCart(@RequestBody CreateCartRequestDTO createCartRequestDTO){
        log.info("|| createCart is called from CartController class ||");

        return new ResponseEntity<>(cartService.createCart(createCartRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/cart/{cartId}")
    public ResponseEntity<Cart> getCartByCartId(@PathVariable String cartId){
        log.info("|| getCartByCartId is called from CartController class ||");

        return new ResponseEntity<>(cartService.getCartByCartId(cartId), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Cart>> getAllCartByUserId(@PathVariable String userId){
        log.info("|| getAllCartByUserId is called from CartController class ||");

        return new ResponseEntity<>(cartService.getAllCartByUserId(userId), HttpStatus.OK);
    }

    @DeleteMapping("/cart/{cartId}")
    public ResponseEntity<String> deleteCartByCartId(@PathVariable String cartId){
        log.info("|| deleteCartByCartId is called from CartController class ||");

        return new ResponseEntity<>(cartService.deleteCartByCartId(cartId), HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> deleteAllCartByUserId(@PathVariable String userId){
        log.info("|| deleteAllCartByUserId is called from CartController class ||");

        return new ResponseEntity<>(cartService.deleteAllCartByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Cart>> getAllCart(){
        log.info("|| getAllCart is called from CartController class ||");

        return new ResponseEntity<>(cartService.getAllCart(), HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAllCart(){
        log.info("|| deleteAllCart is called from CartController class ||");

        return new ResponseEntity<>(cartService.deleteAllCart(), HttpStatus.OK);
    }

    @PutMapping("/cart/add/{cartId}")
    public ResponseEntity<String> addAItemByCartId(@PathVariable String cartId, @RequestBody Item item){
        log.info("|| addAItemByCartId is called from CartController class ||");

        return new ResponseEntity<>(cartService.addAItemByCartId(cartId, item), HttpStatus.OK);
    }

    @PutMapping("/cart/remove/{cartId}")
    public ResponseEntity<String> removeItemByCartId(@PathVariable String cartId, @RequestBody Item item){
        log.info("|| removeItemByCartId is called from CartController class ||");

        return new ResponseEntity<>(cartService.removeItemByCartId(cartId, item), HttpStatus.OK);
    }
}
