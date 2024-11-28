package com.bookstore.Book.mapper;

import com.bookstore.Book.dto.BookDto;
import com.bookstore.Book.model.Book;

public class BookMapper {

    public static BookDto mapDtoToBook(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setBookAuthor(book.getBookAuthor());
        bookDto.setBookName(book.getBookName());
        bookDto.setDescription(book.getDescription());
        bookDto.setLogo(book.getLogo());
        bookDto.setPrice(book.getPrice());
        bookDto.setQuantity(book.getQuantity());
        return bookDto;
    }
}
