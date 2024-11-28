package com.bookstore.Order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {


        @Id
        @GeneratedValue
        private long orderItemId;

        private long bookId;
        private float price;
        private int quantity;

        @ManyToOne
        @JoinColumn(name = "order_id")
        @JsonIgnore
        private Order order;


}
