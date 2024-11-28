package com.bookstore.Cart.dto;

import com.bookstore.Cart.external.Book;
import com.bookstore.Cart.external.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartDto {

    private Long cartId;
    private User user;
    private Book book;
    private long quantity;

    private long totalPrice;
}
