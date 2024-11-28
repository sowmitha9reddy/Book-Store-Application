package com.bookstore.Cart.dao;

import com.bookstore.Cart.dto.CartDto;
import com.bookstore.Cart.model.Cart;

import java.util.List;

public interface CartDao  {

    public Cart addTocart(Cart cart);

    public CartDto updateBook(long cartId, int quantity);

    public void removeFromCart(long cartId);

    public void removeFromCartForUser(Long userId);



    public List<Cart> getAllCartItemsForUser(Long userId);

    public List<CartDto> getAllCartItems();

}
