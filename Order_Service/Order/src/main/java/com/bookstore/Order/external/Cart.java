package com.bookstore.Order.external;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cart {
    private Long cartId;
    private Long userId;
    private Long bookId;
    private int quantity;
    private long totalPrice;
}
