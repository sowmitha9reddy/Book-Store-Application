package com.bookstore.Order.cilents;


import com.bookstore.Order.external.Address;
import com.bookstore.Order.external.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="USER")
public interface UserClient {

        @GetMapping("/user/getUserByToken")
        public User getUser(@RequestHeader("Authorization") String authHeader);


        @GetMapping(value="/user/getUser/{id}")
        //  @PreAuthorize("hasAuthority('USER')")
        public User getUserById(@PathVariable long id) ;
}

