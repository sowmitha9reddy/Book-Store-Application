package com.example.use_micrroservice.dto;

import com.example.use_micrroservice.model.Address;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
    @NotEmpty
    @Pattern(regexp = "^[A-Z][a-z]{2,}$",message="Invalid Name")
    private String firstName;


    @NotEmpty
    @Pattern(regexp = "^[A-Z][a-z]{2,}$",message="Invalid Name")
    private String lastName;


    private LocalDate dob;
    private String username;
    private String password;

    @NotEmpty
    @Pattern(regexp = "^[A-Za-z0-9#$%*]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z]{2,7}$",message = "Invalid Email Address")

    private String emailId;

    private String role;

     private List<Address> address;
}
