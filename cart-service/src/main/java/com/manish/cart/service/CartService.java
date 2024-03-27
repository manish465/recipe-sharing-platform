package com.manish.cart.service;

import com.manish.cart.dto.CreateCartRequestDTO;
import com.manish.cart.dto.RemoveAllItemDTO;
import com.manish.cart.entity.Cart;
import com.manish.cart.entity.Item;
import com.manish.cart.exception.ApplicationException;
import com.manish.cart.repository.CartRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Validated
public class CartService {
    private final CartRepository cartRepository;

    public String createCart(@Valid CreateCartRequestDTO createCartRequestDTO) {
        log.info("|| createCart is called from CartService class ||");


        Cart cart = Cart.builder()
                .userId(createCartRequestDTO.getUserId())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .itemList(new ArrayList<>())
                .build();

        return "Cart create with cart id : " + cartRepository.save(cart).getCartId();
    }

    public Cart getCartByCartId(String cartId){
        log.info("|| getCartByCartId is called from CartService class ||");

        Optional<Cart> optionalCart = cartRepository.findById(cartId);

        if(optionalCart.isEmpty()){
            throw new ApplicationException("Cart dose not exist");
        }

        return optionalCart.get();
    }

    public List<Cart> getAllCartByUserId(String userId){
        log.info("|| getAllCartByUserId is called from CartService class ||");

        return cartRepository.findAllByUserId(userId);
    }

    public String deleteCartByCartId(String cartId){
        log.info("|| deleteCartByCartId is called from CartService class ||");

        Optional<Cart> optionalCart = cartRepository.findById(cartId);

        if(optionalCart.isEmpty()){
            throw new ApplicationException("Cart dose not exist");
        }

        cartRepository.deleteById(cartId);

        return "Cart delete with cart id : " + cartId;
    }

    public String deleteAllCartByUserId(String userId){
        log.info("|| deleteAllCartByUserId is called from CartService class ||");

        if(cartRepository.findAllByUserId(userId).isEmpty()){
            throw new ApplicationException("User dose not have any cart");
        }

        cartRepository.deleteAllByUserId(userId);
        return "All carts are deleted with user id : " + userId;
    }

    public List<Cart> getAllCart(){
        log.info("|| getAllCart is called from CartService class ||");

        return cartRepository.findAll();
    }

    public String deleteAllCart(){
        log.info("|| deleteAllCart is called from CartService class ||");

        cartRepository.deleteAll();

        return "Cart table cleared";
    }

    private boolean isProductExistInItemListBy(String productId, List<Item> itemList){
        for(Item item : itemList){
            if(Objects.equals(item.getProductId(), productId)){
                return true;
            }
        }

        return false;
    }

    public String addAItemByCartId(String cartId, @Valid Item item){
        log.info("|| addAItemByCartId is called from CartService class ||");

        Optional<Cart> optionalCart = cartRepository.findById(cartId);

        if(optionalCart.isEmpty()){
            throw new ApplicationException("Cart dose not exist");
        }

        List<Item> itemList = optionalCart.get().getItemList();

        if(!isProductExistInItemListBy(item.getProductId(), itemList)){
            itemList.add(item);
        }else {
            for(Item i : itemList){
                if(Objects.equals(i.getProductId(), item.getProductId())){
                    i.setCount(i.getCount() + item.getCount());
                }
            }
        }

        optionalCart.get().setItemList(itemList);
        return "Item added to cart with cart id : " + cartRepository.save(optionalCart.get()).getCartId();
    }

    public String removeItemByCartId(String cartId, @Valid Item item){
        log.info("|| removeItemByCartId is called from CartService class ||");

        Optional<Cart> optionalCart = cartRepository.findById(cartId);

        if(optionalCart.isEmpty()){
            throw new ApplicationException("Cart dose not exist");
        }

        List<Item> itemList = optionalCart.get().getItemList();

        if(!isProductExistInItemListBy(item.getProductId(), itemList)){
            throw new ApplicationException("Item is not in the cart");
        }

        for(Item i : itemList){
            if(i.getProductId().equals(item.getProductId())){
                if(i.getCount() < item.getCount()) {
                    throw new ApplicationException("Cannot remove item from cart because of item count");
                }

                i.setCount(i.getCount() - item.getCount());

                if(i.getCount() == 0){
                    itemList.remove(i);
                }

                break;
            }
        }

        optionalCart.get().setItemList(itemList);

        return "Item removed from cart with cart id : " + cartRepository.save(optionalCart.get()).getCartId();
    }

    public String removeAllItemByCartId(String cartId, @Valid RemoveAllItemDTO removeAllItemDTO) {
        log.info("|| removeAllItemByCartId is called from CartService class ||");

        Optional<Cart> optionalCart = cartRepository.findById(cartId);

        if(optionalCart.isEmpty()){
            throw new ApplicationException("Cart dose not exist");
        }

        List<Item> itemList = optionalCart.get().getItemList();

        if(!isProductExistInItemListBy(removeAllItemDTO.getProductId(), itemList)){
            throw new ApplicationException("Item is not in the cart");
        }

        for(Item i : itemList){
            if(i.getProductId().equals(removeAllItemDTO.getProductId())){
                itemList.remove(i);
                break;
            }
        }

        optionalCart.get().setItemList(itemList);

        return "Item removed from cart with cart id : " + cartRepository.save(optionalCart.get()).getCartId();
    }
}
