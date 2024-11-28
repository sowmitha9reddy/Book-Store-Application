package com.bookstore.Order.external;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address {

    private long id;
    private String street;
    private String city;
    private String state;
    private long zipcode;



}
