package com.bookstore.Order.cilents;

import com.bookstore.Order.external.Cart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="CART")
public interface CartClient {

    @DeleteMapping(value="/Cart/deleteCartDetails/{userId}")
    public ResponseEntity<String> removeByUserID(@PathVariable long userId);

    @GetMapping(value="/Cart/getCartItemsForUser/{userId}")
    public List<Cart> getAllCartItemsForUser(@PathVariable long userId);

    @GetMapping(value="/Cart/getItemByCartId/{cartId}")
    public Cart getItemByCartId(@PathVariable long cartId);

    @DeleteMapping(value="/Cart/deleteCartDetailsByCartID/{cartId}")
     public void removeByCartId( @PathVariable long cartId);
}
