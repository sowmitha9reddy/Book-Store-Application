package com.example.use_micrroservice.service;

import com.example.use_micrroservice.dto.UserDto;
import com.example.use_micrroservice.model.User;

import java.util.List;

public interface UserDao {

    public UserDto registerUser(UserDto userDto);

    public UserDto updateUser(long id, UserDto userDto);

    public void deleteUser(long id);

    public User getUserById(long id);

    public List<UserDto> getUsers();

    public User getUserByUserName(String username);
}
