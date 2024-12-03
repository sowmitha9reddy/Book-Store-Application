package com.bookstore.Book.dto;

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
public class BookDto {


    private String bookName;
    private String bookAuthor;


    private String description;


//    private byte[] logo; // To store the book logo as binary data
   private String logo;


    private long price;


    private int quantity;
}
