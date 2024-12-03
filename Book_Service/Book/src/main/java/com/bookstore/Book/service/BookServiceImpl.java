package com.bookstore.Book.service;

import com.bookstore.Book.dao.BookServiceDao;
import com.bookstore.Book.dto.BookDto;
import com.bookstore.Book.exceptionhandler.BookNotFoundException;
import com.bookstore.Book.mapper.BookMapper;
import com.bookstore.Book.model.Book;
import com.bookstore.Book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl  implements BookServiceDao {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookDto addBook(BookDto bookDto, MultipartFile file) throws IOException
  {


       Book book = new Book(bookDto);
      //book.setLogo(file.getBytes());
      book.setLogo(file.getOriginalFilename());
       return BookMapper.mapDtoToBook(bookRepository.save(book));

    }

    @Override
    public BookDto updateBook(long bookId, BookDto bookDto, MultipartFile file) throws IOException {
        Book book=bookRepository.findById(bookId).orElseThrow(() ->new BookNotFoundException("Book Id Not Found"));

        book.setBookAuthor(bookDto.getBookAuthor());
        book.setBookName(bookDto.getBookName());
        book.setDescription(bookDto.getDescription());
       // book.setLogo(file.getBytes());
        book.setLogo(file.getOriginalFilename());
        book.setPrice(bookDto.getPrice());
        book.setQuantity(bookDto.getQuantity());

        //emailService.sendEmail(studentDto.getEmail(), "Your Details updated  successfully",student.toString());
        return  BookMapper.mapDtoToBook(bookRepository.save(book));

    }

    @Override
    public void deleteBook(long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public Book getBookById(long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() ->new BookNotFoundException("Book Id Not Found"));
    }

    @Override
    public List<BookDto> getBooks() {
        return bookRepository.findAll().
                stream()
                .map(book ->BookMapper.mapDtoToBook(book))
                .collect(Collectors.toList());
    }

    @Override
    public boolean increaseBookQuantity(long bookId, int quantity) {
        Book book=bookRepository.findById(bookId).orElseThrow(() ->new BookNotFoundException("Book Id Not Found"));
            int previousQuantity=book.getQuantity();

            book.setQuantity(previousQuantity+quantity);
            bookRepository.save(book);
            return true;
    }

    @Override
    public boolean reduceBookQuantity(long bookId, int quantity) {
        Book book=bookRepository.findById(bookId).orElseThrow(() ->new BookNotFoundException("Book Id Not Found"));
        int previousQuantity=book.getQuantity();

        book.setQuantity(previousQuantity-quantity);
        bookRepository.save(book);
        return true;
    }

    @Override
    public boolean changeBookPrice(long bookId, int price) {
        Book book=bookRepository.findById(bookId).orElseThrow(() ->new BookNotFoundException("Book Id Not Found"));


        book.setQuantity(price);
        bookRepository.save(book);
        return true;
    }


}



