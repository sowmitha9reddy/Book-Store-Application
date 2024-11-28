package com.bookstore.Cart.cilents;

import com.bookstore.Cart.external.Book;
import com.bookstore.Cart.external.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="BOOK")
public interface BookClient {

    @GetMapping(value="/books/getBook/{bookId}")
    public Book getBook(@PathVariable  long bookId);

    @PostMapping(value = "/books/reduceQuantity/{bookId}")
    public boolean reduceBookQuantity(@PathVariable long bookId,@RequestParam int quantity);

    @PostMapping(value = "/books/increaseQuantity/{bookId}")
    public boolean increaseBookQuantity(@PathVariable long bookId,@RequestParam int quantity);
}
