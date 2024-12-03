package com.bookstore.Order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="OrderDetails")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue
    long orderId;

    @CreationTimestamp
    LocalDate orderDate;

    float totalPrice;
    int quantity;
    long addressId;

   long userId;

    boolean cancel;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<OrderItem> orderItems = new ArrayList<>();

    @Override
    public String toString() {
        return "Order{id=" + orderId + ", orderDate=" + orderDate + ", totalAmount=" +totalPrice + "}";
    }


}
