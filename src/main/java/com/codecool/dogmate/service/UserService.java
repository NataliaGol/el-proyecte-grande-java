package com.codecool.dogmate.service;

import com.codecool.dogmate.dto.UserRequest;
import com.codecool.dogmate.dto.UserResponse;
import com.codecool.dogmate.entity.User;
import com.codecool.dogmate.mapper.UserMapper;
import com.codecool.dogmate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Collection<UserResponse> getAllUsers() {
        return userMapper.toResponse(userRepository.findAllByIsActiveTrue());
    }

    public void registerUser(UserRequest dto) {
        User entity = userMapper.toEntity(dto);
        userRepository.registerUser(entity);
        // todo send email?
    }

}
