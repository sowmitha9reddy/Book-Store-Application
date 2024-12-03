package com.bookstore.Book.model;

import com.bookstore.Book.dto.BookDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Book")

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class Book {

    @Id
    @GeneratedValue
    private long bookId;

   @Column(nullable = false)
   private String bookName;

    @Column(nullable = false)
    private String bookAuthor;

    @Column(length = 1000)
    private String description;

//    @Lob
//    private byte[] logo; // To store the book logo as binary data
    private String logo;

    @Column(nullable = false)
    private long price;

    @Column(nullable = false)
    private int quantity;

    public Book(BookDto bookDto) {
        this.bookName = bookDto.getBookName();
        this.bookAuthor = bookDto.getBookAuthor();
        this.description = bookDto.getDescription();
        this.logo = bookDto.getLogo();
        this.price = bookDto.getPrice();
        this.quantity = bookDto.getQuantity();

    }
}
