package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.LoginRequest;
import com.codecool.dogmate.dto.RegisterRequest;
import com.codecool.dogmate.dto.UserResponse;
import com.codecool.dogmate.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    public ResponseEntity<HttpStatus> registerUser(@RequestBody RegisterRequest dto) {
        userService.registerUser(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addNewUser(@RequestBody RegisterRequest dto) {
        userService.addNewUser(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public UserResponse login(HttpServletResponse response, @RequestBody LoginRequest dto) {
        return userService.loginUser(response, dto);
    }

    @PostMapping("/logout")
    public void logout(HttpServletResponse response) {
        userService.logout(response);
    }

}
