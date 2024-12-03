package com.bookstore.Order.util;

import com.bookstore.Order.cilents.BookClient;
import com.bookstore.Order.cilents.CartClient;
import com.bookstore.Order.cilents.UserClient;
import com.bookstore.Order.external.Cart;
import com.bookstore.Order.external.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Utility {


    @Autowired
    private CartClient cartClient;

    @Autowired
    private UserClient userClient;

    @Autowired
    private BookClient bookClient;

    public User getAuthenticatedAdminUser(String authHeader) {
        User user = userClient.getUser(authHeader);
        System.out.println(user);


        if (user != null) {
            return user;
        }
        return null;
    }

    public User getUser(long userId) {
        return userClient.getUserById(userId);
    }

    public List<Cart> cartItemsForUser(long userId){

      return cartClient.getAllCartItemsForUser(userId);

    }

    public Cart getCartByCartId(long cartId){

        return cartClient.getItemByCartId(cartId);

    }

    public void clearCartForUser(long userId) {
        cartClient.removeByUserID(userId);
    }



    public void changeBookQuantity(long bookId, int quantity) {
        bookClient.increaseBookQuantity(bookId,quantity);
    }


    public void clearCartByCartId(long cartId) {
        cartClient.removeByCartId(cartId);
    }


}
