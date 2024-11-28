package com.bookstore.Order.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;


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
    private List<Address> address;

}
