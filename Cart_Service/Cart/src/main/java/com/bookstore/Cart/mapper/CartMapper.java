package com.bookstore.Cart.mapper;

import com.bookstore.Cart.cilents.BookClient;
import com.bookstore.Cart.cilents.UserClient;
import com.bookstore.Cart.dto.CartDto;
import com.bookstore.Cart.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {

    private static UserClient userClient;
    private static BookClient bookClient;

    public CartMapper(UserClient userClient, BookClient bookClient) {
        CartMapper.userClient = userClient;
        CartMapper.bookClient = bookClient;
    }

    public static CartDto mapDtoToCart(Cart cart){
        CartDto cartDto=new CartDto();
        cartDto.setCartId(cart.getCartId());
        //System.out.println(cart.getBookId());
       // System.out.println(cart.getUserId());
        cartDto.setBook(bookClient.getBook(cart.getBookId()));
        cartDto.setUser(userClient.getUser(cart.getUserId()));
       cartDto.setQuantity(cart.getQuantity());
       cartDto.setTotalPrice(cart.getTotalPrice());

        return cartDto;
    }
}
