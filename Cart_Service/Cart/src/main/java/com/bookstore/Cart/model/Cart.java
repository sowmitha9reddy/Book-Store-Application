package com.bookstore.Cart.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;

@Entity
@Table(name = "cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "book_id", nullable = false)
    private Long bookId;

    private int quantity;

    private long totalPrice;

    // Utility method to calculate the total price based on quantity and book price
    public void calTotalPrice(long bookPrice) {
        this.totalPrice = bookPrice *(this.quantity);
    }
}

