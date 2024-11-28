package com.example.use_micrroservice.controller;

import com.example.use_micrroservice.config.CustomUserDetails;
import com.example.use_micrroservice.dto.AuthRequest;
import com.example.use_micrroservice.dto.UserDto;
import com.example.use_micrroservice.model.User;
import com.example.use_micrroservice.service.JwtService;
import com.example.use_micrroservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

        @Autowired
        private UserService userService;

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private JwtService jwtService;

        @PostMapping("/register")
        public String addNewUser(@RequestBody UserDto userDto) {
            userService.registerUser(userDto);
            return "user added sucessfully";
        }

        @PostMapping("/token")
        public String getToken(@RequestBody AuthRequest authRequest) {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            if (authenticate.isAuthenticated()) {
                return userService.generateToken(authRequest.getUsername());
            } else {
                throw new RuntimeException("invalid access");
            }
        }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        userService.validateToken(token);
        return "Token is valid";
    }

    @GetMapping("/getUserByToken")
    public ResponseEntity<?> getUserByToken(@RequestHeader("Authorization") String authHeader) {
        System.out.println(authHeader);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            // Validate the token
            jwtService.validateToken(token);

            // Extract username
            String username = jwtService.extractUsername(token);
            System.out.println("Extracted User name: " + username);

            // Get UserDto using the extracted username
            try {
                User user = userService.getUserByUserName(username);
                System.out.println("Token validated, getting user: " + user);
                return new ResponseEntity<>(user, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>("Error fetching user data", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        }



    @GetMapping(value="/getUsers")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserDto> getUsers() {
        return  userService.getUsers();

    }

    @GetMapping(value="/getUser/{id}")

    public User getUser(@PathVariable long id) {
        System.out.println(userService.getUserById(id));
        return  userService.getUserById(id);
    }

    }
