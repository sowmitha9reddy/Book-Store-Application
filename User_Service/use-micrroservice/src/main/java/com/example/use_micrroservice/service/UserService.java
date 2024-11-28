package com.example.use_micrroservice.service;

import com.example.use_micrroservice.dto.UserDto;
import com.example.use_micrroservice.globalexceptionHandling.UserNotFoundException;
import com.example.use_micrroservice.mapper.UserMapper;
import com.example.use_micrroservice.model.Address;
import com.example.use_micrroservice.model.User;
import com.example.use_micrroservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class UserService implements UserDao {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user=new User(userDto);
        List<Address> addresses = userDto.getAddress();

        if (addresses != null) {
            addresses.forEach(address -> address.setUser(user));

        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public UserDto updateUser(long id, UserDto userDto) {

        User user=new User();
        user.setAddress(userDto.getAddress());

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return UserMapper.mapToUserDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);

    }

    @Override
    public User getUserById(long id) {
        return  userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("ID not Found"));
    }



    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll().stream().map(user ->UserMapper.mapToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public User getUserByUserName(String username) {

        Optional<User> user=userRepository.findByUsername(username);

        return user.get();
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

//    public User getUser(String username) {
//        return userRepository.findByUsername(username).get();
//    }
}
