package com.bookstore.Cart.external;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {

    private long bookId;


    private String bookName;


    private String bookAuthor;


    private String description;


    //private byte[] logo;
    private String logo;



    private long price;

    private int quantity;



}
