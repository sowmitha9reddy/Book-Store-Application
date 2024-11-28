package com.bookstore.Book.controller;



import com.bookstore.Book.dao.BookServiceDao;
import com.bookstore.Book.dto.BookDto;
import com.bookstore.Book.model.Book;
import com.bookstore.Book.service.BookServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value="/books")
public class BookController {

    BookServiceDao bookServiceDao=new BookServiceImpl();
    @Autowired
    private BookServiceImpl bookServiceImpl;
    

     //Api to add book
    @PostMapping(value="/addBook",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<?> addBook(@Valid @RequestPart("bookDto") String bookDto, @RequestPart("logo") MultipartFile file) throws IOException
     {
         try
         {       BookDto bookDtoo= new ObjectMapper().readValue(bookDto, BookDto.class);
                 BookDto book=bookServiceImpl.addBook(bookDtoo,file);
                 return new ResponseEntity<>(book, HttpStatus.CREATED);

         }
         catch (Exception e)
         {
             return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
         }

    }

    //api to get single book based on Book ID.
    @GetMapping(value="/getBook/{bookId}")
    public Book getBook(@PathVariable  long bookId) {

            System.out.println(bookServiceImpl.getBookById(bookId));
            return bookServiceImpl.getBookById(bookId);

    }

   // api to get the books
    @GetMapping(value="/getBooks")
    public List<BookDto> getBooks() {
        return  bookServiceImpl.getBooks();


    }
    // api to delete book from database
    @DeleteMapping(value="/delBook/{bookId}")
    public void deleteBook(@PathVariable long bookId) {
            bookServiceImpl.deleteBook(bookId);


    }

  // api to update book details
    @PostMapping(value = "/updateBook/{bookId}")
    public BookDto updateBook(@PathVariable long bookId,@Valid @RequestPart("bookDto") BookDto bookDto, @RequestPart("logo") MultipartFile file) {

            try {
                return bookServiceImpl.updateBook(bookId, bookDto,file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

    }

   // api to change the book quantity
    @PostMapping(value = "/reduceQuantity/{bookId}")
    public boolean reduceBookQuantity(@PathVariable long bookId,@RequestParam int quantity) {

           return bookServiceImpl.reduceBookQuantity(bookId,quantity);
    }

    @PostMapping(value = "/increaseQuantity/{bookId}")
    public boolean increaseBookQuantity(@PathVariable long bookId,@RequestParam int quantity) {

        return bookServiceImpl.increaseBookQuantity(bookId,quantity);
    }

    @PostMapping(value = "/changePrice/{bookId}")
    public boolean changeBookPrice(@PathVariable long bookId, int price) {
           return bookServiceImpl.changeBookPrice(bookId,price);

    }



}
