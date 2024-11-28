package com.bookstore.Cart.repo;

import com.bookstore.Cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {


    List<Cart> findAllByUserId(Long userId);


    void deleteAllByUserId(Long userId);
}
