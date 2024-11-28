package com.example.use_micrroservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

//@Entity
//@Table(name = "RefreshTokens")
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@ToString
//@Builder
//
//
//public class RefreshToken {
//
//    @Id
//    @GeneratedValue
//    private long id;
//    private String token;
//    private Instant expiryDate;
//
////    @ManyToOne
////    @JoinColumn(name="user_id")
////    @JsonIgnore
////    private User user;
//}