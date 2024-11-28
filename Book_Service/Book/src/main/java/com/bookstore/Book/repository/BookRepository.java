package com.bookstore.Book.repository;

import com.bookstore.Book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
}
