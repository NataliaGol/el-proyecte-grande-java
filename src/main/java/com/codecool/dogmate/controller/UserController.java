package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.UserRequest;
import com.codecool.dogmate.dto.UserResponse;
import com.codecool.dogmate.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Collection<UserResponse> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<HttpStatus> registerUser(@RequestBody UserRequest dto) {
        userService.registerUser(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
