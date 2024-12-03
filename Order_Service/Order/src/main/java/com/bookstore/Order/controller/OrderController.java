package com.bookstore.Order.controller;


import com.bookstore.Order.external.User;
import com.bookstore.Order.sevice.OrderService;
import com.bookstore.Order.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Order")
public class OrderController {

     @Autowired
     private Utility utility;

     @Autowired
     private OrderService orderService;


    @PostMapping(value="/placeOrder/{addressId}")
    public ResponseEntity<?> placeOrder(@RequestHeader("Authorization") String authHeader,@PathVariable long addressId) {
        System.out.println(authHeader);
        User isAdmin = utility.getAuthenticatedAdminUser(authHeader);
        System.out.println(isAdmin);
        long userId = isAdmin.getUserId();
        return new ResponseEntity<>(orderService.PlaceOrder(userId,addressId), HttpStatus.OK);
    }


    @PostMapping(value="/placeOrder/{addressId}/{cartId}")
    public ResponseEntity<?> placeOrderByCartId(@RequestHeader("Authorization") String authHeader,@PathVariable long addressId,@PathVariable long cartId) {
        System.out.println(authHeader);
        User isAdmin = utility.getAuthenticatedAdminUser(authHeader);
        System.out.println(isAdmin);
        //long userId = isAdmin.getUserId();
        return new ResponseEntity<>(orderService.PlaceOrderByCartId(isAdmin,addressId,cartId), HttpStatus.OK);
    }

    @DeleteMapping(value="/canceltheOrder/{orderId}")
    public ResponseEntity<?> cancelOrder(@RequestHeader("Authorization") String authHeader,@PathVariable long orderId){
        User isAdmin = utility.getAuthenticatedAdminUser(authHeader);
        System.out.println(isAdmin);
        long userId = isAdmin.getUserId();
        return new ResponseEntity<>(orderService.cancelOrder(orderId),HttpStatus.OK);
    }

    @GetMapping(value = "/getAllOrders")
    public ResponseEntity<?> getAllOrders(@RequestHeader("Authorization") String authHeader){
        User isAdmin = utility.getAuthenticatedAdminUser(authHeader);
        System.out.println(isAdmin);
        return new ResponseEntity<>(orderService.getAllOrders(),HttpStatus.OK);
    }

    @GetMapping(value = "/getAllOrdersOfUser")
    public ResponseEntity<?> getAllOrdersOfUser(@RequestHeader("Authorization") String authHeader){
        User isAdmin = utility.getAuthenticatedAdminUser(authHeader);
        System.out.println(isAdmin);
        long userId = isAdmin.getUserId();

        return new ResponseEntity<>(orderService.getAllOrdersForUser(userId),HttpStatus.OK);
    }


}
