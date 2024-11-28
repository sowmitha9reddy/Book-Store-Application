package com.example.use_micrroservice.model;

import com.example.use_micrroservice.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="UsersInfo")

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "address")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDate dob;



    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate registeredDate;

    @CreationTimestamp
    private LocalDate updatedDate;


    private String username;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String emailId;

    private String role;

    @OneToMany(mappedBy = "user",cascade= CascadeType.ALL)
    List<Address> address;

//    @OneToMany(mappedBy = "usersInfo",cascade=CascadeType.ALL)
//    private List<RefreshToken> refreshToken;

    public User(UserDto userDto) {
        this.firstName = userDto.getFirstName();
        this.lastName = userDto.getLastName();
        this.dob = userDto.getDob();
        this.username=userDto.getUsername();
        this.password=userDto.getPassword();
        this.emailId = userDto.getEmailId();
        this.role = userDto.getRole();
        this.address=userDto.getAddress();



    }
}
