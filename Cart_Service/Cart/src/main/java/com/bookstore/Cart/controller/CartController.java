package com.bookstore.Cart.controller;


import com.bookstore.Cart.cilents.BookClient;
import com.bookstore.Cart.cilents.UserClient;
import com.bookstore.Cart.dto.CartDto;
import com.bookstore.Cart.exceptionHandling.CartNotFoundException;
import com.bookstore.Cart.external.Book;

import com.bookstore.Cart.external.User;
import com.bookstore.Cart.mapper.CartMapper;
import com.bookstore.Cart.model.Cart;



import com.bookstore.Cart.service.CartServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Cart")
public class CartController {

    @Autowired
    private CartServiceImpl cartServiceImpl;

    @Autowired
    private UserClient userClient;

    @Autowired
    private BookClient bookClient;

    private User getAuthenticatedAdminUser(String authHeader) {

            User user = userClient.getUser(authHeader);
            System.out.println(user);

            System.out.println(user);
            if (user != null) {
                return user;
            }

        return null;
    }

    public Book getBookById(Long bookId)
    {
        Book book = bookClient.getBook(bookId);
        return  book;
    }


    @PostMapping(value="/addToCart/{bookId}")
    public CartDto addToCart(@RequestHeader("Authorization") String authHeader, @PathVariable long bookId)  {
        System.out.println(authHeader);
        User isAdmin=getAuthenticatedAdminUser(authHeader);
        System.out.println(isAdmin);
        if(isAdmin!=null)
        {       long userId=isAdmin.getUserId();
                Book book=getBookById(bookId);
                System.out.println(book);
                return cartServiceImpl.adduserIdAndBookDetailsToCart(userId,book);

        }
        return null;
    }

   //updating the cart details
    @PutMapping (value="/updateCartDetails/{cartId}/{quantity}")
    public CartDto increaseCartQuantity(@RequestHeader("Authorization") String authHeader,@PathVariable long cartId,@PathVariable int quantity) {

       return cartServiceImpl.increaseCartQuantity(cartId,quantity);

    }

    @PutMapping (value="/updateCartDetailsDecreaseCart/{cartId}/{quantity}")
    public CartDto decreaseCartQuantity(@RequestHeader("Authorization") String authHeader,@PathVariable long cartId,@PathVariable int quantity) {

        return cartServiceImpl.decreaseCartQuantity(cartId,quantity);

    }

//    @DeleteMapping(value="/deleteCartDetails/{cartId}")
//    public  ResponseEntity<String> removeFromCart(@PathVariable long cartId) {
//       cartServiceImpl.removeFromCart(cartId);
//       return new ResponseEntity<>("Deleted cart items  successfully",HttpStatus.CREATED);
//    }

    @DeleteMapping(value="/deleteCartDetails/{userId}")
    public ResponseEntity<String>  removeByUserID(@PathVariable long userId) {
           Long userId2=userId;
            cartServiceImpl.removeFromCartForUser(userId2);
            return new ResponseEntity<>("Deleted cart item using user id successfully", HttpStatus.CREATED);


        }

   @GetMapping(value="/getCartItemsForUser")
    public List<Cart> getAllCartItemsForUser(@RequestHeader("Authorization") String authHeader) {
       System.out.println(authHeader);
       User isAdmin=getAuthenticatedAdminUser(authHeader);
       System.out.println(isAdmin);

       if(isAdmin!=null) {
           long userId = isAdmin.getUserId();
           System.out.println("Show the userId" + userId);
           System.out.println(cartServiceImpl.getAllCartItemsForUser(userId));
           return cartServiceImpl.getAllCartItemsForUser(userId);
       }
        return null;
    }

    @GetMapping(value="/getCartItemsForUser/{userId}")
    public List<Cart> getAllCartItemsForUserByUserId(@PathVariable long userId ) {


//           System.out.println(userId);
               Long userId2=userId;
//            System.out.println("Show the userId" + userId2);
            System.out.println(cartServiceImpl.getAllCartItemsForUser(userId2));
            return cartServiceImpl.getAllCartItemsForUser(userId2);

    }



    @GetMapping(value="/getCartItems")
    public List<CartDto> getAllCartItems() {
      return cartServiceImpl.getAllCartItems();

    }

    @GetMapping(value="/getItemByCartId/{cartId}")
    public Cart getItemByCartId(@PathVariable long cartId) {
        return cartServiceImpl.getItemByCartId(cartId);

    }


    @DeleteMapping(value="/deleteCartDetailsByCartID/{cartId}")
    public void removeByCartId(@PathVariable long cartId){
        cartServiceImpl.removeFromCartByCartId(cartId);
    }





}
