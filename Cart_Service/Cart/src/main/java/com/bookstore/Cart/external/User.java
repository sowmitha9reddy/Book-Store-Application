package com.bookstore.Cart.external;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;


@Data
@NoArgsConstructor

@AllArgsConstructor
@ToString
public class User {

    private long userId;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String username;
    private String password;
    private String emailId;
    private String role;

}
