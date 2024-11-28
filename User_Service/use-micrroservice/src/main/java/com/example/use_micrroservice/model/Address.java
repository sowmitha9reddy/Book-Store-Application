package com.example.use_micrroservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="Address")

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Address {
    @Id
    @GeneratedValue
    private long id;
    private String street;
    private String city;
    private String state;
    private long zipcode;


    @ManyToOne
    @JoinColumn(name="user_Id")
    @JsonIgnore
    private User user;
}
