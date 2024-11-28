package com.bookstore.Book.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String username;
    private String password;
    private String emailId;
    private String role;
}
