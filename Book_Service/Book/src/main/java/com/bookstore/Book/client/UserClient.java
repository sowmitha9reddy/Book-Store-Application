package com.bookstore.Book.client;

import com.bookstore.Book.external.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "USER")
public interface UserClient {


        @GetMapping("/user/getUserByToken")
        public User getUserByToken(@RequestHeader("Authorization") String authHeader);






}
