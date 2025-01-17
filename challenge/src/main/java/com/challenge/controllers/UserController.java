package com.challenge.controllers;


import com.challenge.domain.user.User;
import com.challenge.dtos.UserDto;
import com.challenge.repositories.UserRepository;
import com.challenge.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/users")

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDto userDto) throws Exception {

        User newUser = userService.createUser(userDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){

        List<User> existingUser = userService.getAllUsers();

        return ResponseEntity.status(HttpStatus.OK).body(existingUser);

    }
}
