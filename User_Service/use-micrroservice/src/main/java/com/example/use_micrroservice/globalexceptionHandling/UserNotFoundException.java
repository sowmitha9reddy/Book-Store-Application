package com.example.use_micrroservice.globalexceptionHandling;

public class UserNotFoundException  extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);

    }
}