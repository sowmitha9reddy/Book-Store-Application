package com.bookstore.Book.dao;

import com.bookstore.Book.dto.BookDto;

import com.bookstore.Book.model.Book;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BookServiceDao {

  //  public BookDto addBook(BookDto bookDto, MultipartFile file) throws IOException;
 // public BookDto addBook(BookDto bookDto);


  BookDto addBook(BookDto bookDto, MultipartFile file) throws IOException;

  public BookDto updateBook(long id, BookDto bookDto, MultipartFile file) throws IOException;

    public void deleteBook(long id);

    public Book getBookById(long id);

    public List<BookDto> getBooks();

    public boolean increaseBookQuantity(long bookId, int quantity) ;

  public boolean reduceBookQuantity(long bookId, int quantity) ;

    public boolean changeBookPrice(long bookId, int price);


}
