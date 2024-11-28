package com.example.use_micrroservice.mapper;

import com.example.use_micrroservice.dto.UserDto;
import com.example.use_micrroservice.model.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setDob(user.getDob());
        userDto.setUsername(user.getUsername());
        userDto.setEmailId(user.getEmailId());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        userDto.setAddress(user.getAddress());

        return userDto;

    }
}
