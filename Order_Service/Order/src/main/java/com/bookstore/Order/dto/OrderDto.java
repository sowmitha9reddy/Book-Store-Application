package com.bookstore.Order.dto;


import com.bookstore.Order.external.Address;
import com.bookstore.Order.external.User;
import com.bookstore.Order.model.OrderItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDto {

    LocalDate orderDate;
  //  User user;
    String name;
    String email;
    float totalPrice;
    int quantity;
    Address address;
    private List<OrderItem> orderItems;



}
