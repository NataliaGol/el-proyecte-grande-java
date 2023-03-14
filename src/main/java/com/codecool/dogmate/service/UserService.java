package com.codecool.dogmate.service;

import com.codecool.dogmate.dto.LoginRequest;
import com.codecool.dogmate.dto.RegisterRequest;
import com.codecool.dogmate.dto.UserResponse;
import com.codecool.dogmate.entity.User;
import com.codecool.dogmate.mapper.UserMapper;
import com.codecool.dogmate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public Collection<UserResponse> getAllUsers() {
        return userMapper.toResponse(userRepository.findAllByIsActiveTrue());
    }

    public void registerUser(RegisterRequest dto) {
        User entity = userMapper.toEntity(dto);
        // todo check if email exists in the db, to response with something useful if so
        userRepository.registerUser(entity);
        // todo send email?
    }

    public UserResponse loginUser(HttpServletResponse response, LoginRequest dto) {
        // todo insert into login_attempts
        Optional<User> userByEmail = userRepository.findUserByEmail(dto.getEmail());
        if (userByEmail.isEmpty()
                || !passwordEncoder.matches(dto.getPassword(), userByEmail.get().getPassword())) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return null;
        }
        User user = userByEmail.get();
        Cookie loginCookie = new Cookie("user", user.getName());
        loginCookie.setMaxAge(30 * 60); // 30 minutes
        response.addCookie(loginCookie);
        response.setStatus(HttpStatus.OK.value());
        return userMapper.toResponse(user);
    }

    public void logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("user", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    public void addNewUser(RegisterRequest dto) {
        //ToDo!!!
    }
}
