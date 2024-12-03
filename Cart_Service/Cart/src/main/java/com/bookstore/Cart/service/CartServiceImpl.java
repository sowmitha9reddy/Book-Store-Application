package com.bookstore.Cart.service;

import com.bookstore.Cart.cilents.BookClient;
import com.bookstore.Cart.dao.CartDao;
import com.bookstore.Cart.dto.CartDto;
import com.bookstore.Cart.exceptionHandling.CartNotFoundException;
import com.bookstore.Cart.external.Book;
import com.bookstore.Cart.mapper.CartMapper;
import com.bookstore.Cart.model.Cart;
import com.bookstore.Cart.repo.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartDao {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookClient bookClient;

    @Override
    public Cart addTocart(Cart cart) {

         cartRepository.save(cart);
         System.out.println(cart.getBookId());
        bookClient.reduceBookQuantity(cart.getBookId(), cart.getQuantity());
        return cart;
    }

    public CartDto adduserIdAndBookDetailsToCart(long userId, Book book) {
        Cart cart=new Cart();
        cart.setBookId(book.getBookId());
        System.out.println(book.getBookId());
        cart.setUserId(userId);
        cart.setQuantity(1);

        cart.calTotalPrice(book.getPrice());


        return CartMapper.mapDtoToCart(addTocart(cart));
    }


    @Override
    public CartDto increaseCartQuantity(long cartId, int quantity) {
        Cart cart=cartRepository.findById(cartId).orElseThrow(() ->new CartNotFoundException("Cart Id Not Found"));

        Book book=bookClient.getBook(cart.getBookId());

        if(book.getQuantity()>quantity) {
            cart.setQuantity(cart.getQuantity() + quantity);

        }
        return CartMapper.mapDtoToCart(addTocart(cart));


    }

    @Override
    public CartDto decreaseCartQuantity(long cartId, int quantity) {
        Cart cart=cartRepository.findById(cartId).orElseThrow(() ->new CartNotFoundException("Cart Id Not Found"));
        Book book=bookClient.getBook(cart.getBookId());

        if(book.getQuantity()>quantity) {
            cart.setQuantity(cart.getQuantity() - quantity);
        }
        return CartMapper.mapDtoToCart(addTocart(cart));

    }

    @Override
    public void removeFromCart(long cartId) {
        Cart cart=cartRepository.findById(cartId).orElseThrow(() ->new CartNotFoundException("Cart Id Not Found"));
        bookClient.increaseBookQuantity(cart.getBookId(), cart.getQuantity());
        cartRepository.delete(cart);
    }


    // Removing Cart Details for the user
    //usecase when order is placed then details in the cart will be removed.
    @Override
    @Transactional
    public void removeFromCartForUser(Long userId) {
        List<Cart> cartItems=getAllCartItemsForUser(userId);
        for(Cart item:cartItems){
            bookClient.increaseBookQuantity(item.getBookId(), item.getQuantity());
        }
        cartRepository.deleteAllByUserId(userId);

    }

    @Override
    public List<Cart> getAllCartItemsForUser(Long userId) {

        System.out.println("userID"+userId);
        List<Cart> cartItems=cartRepository.findAllByUserId(userId);
        return cartItems;


    }

    @Override
    public List<CartDto> getAllCartItems() {
        List<Cart> allCartItems=cartRepository.findAll();
        return allCartItems.stream().map(cart ->CartMapper.mapDtoToCart(cart)).collect(Collectors.toList());
    }



    public String deleteCartByUserIdAndCartId(long userId, Long cartId)
    {
        List<Cart> allByUserId = cartRepository.findAllByUserId(userId);
        for(Cart c: allByUserId)
        {
            if(c.getCartId()==cartId)
            {
                cartRepository.deleteById(cartId);
                return "cart deleted for user id "+userId+" with cart id "+cartId;

            }
        }
        return "the user does not have this cart access";
    }


    public Cart getItemByCartId(long cartId) {
        return cartRepository.findById(cartId).orElseThrow(() ->new CartNotFoundException("Cart Id Not Found"));
    }

    @Transactional
    public void removeFromCartByCartId(long cartId) {
        Cart item=cartRepository.findById(cartId).orElseThrow(() ->new CartNotFoundException("Cart Id Not Found"));
        bookClient.increaseBookQuantity(item.getBookId(), item.getQuantity());
        cartRepository.delete(item);
    }
}
