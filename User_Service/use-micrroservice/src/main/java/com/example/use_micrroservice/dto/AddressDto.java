package com.example.use_micrroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressDto {
    private String street;
    private String city;
    private String state;
    private long zipcode;
}
