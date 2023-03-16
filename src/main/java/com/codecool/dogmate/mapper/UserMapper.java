package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.RegisterRequest;
import com.codecool.dogmate.dto.UserResponse;
import com.codecool.dogmate.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User toEntity(RegisterRequest dto) {
        if (dto == null) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.name(dto.getName());
        user.email(dto.getEmail());
        user.password(passwordEncoder.encode(dto.getPassword()));

        return user.build();
    }

    public UserResponse toResponse(User entity) {
        if (entity == null) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.id(entity.getId());
        userResponse.name(entity.getName());
        userResponse.email(entity.getEmail());

        return userResponse.build();
    }

    public Collection<User> toEntity(Collection<RegisterRequest> dto) {
        if (dto == null) {
            return null;
        }

        Collection<User> collection = new ArrayList<>(dto.size());
        for (RegisterRequest registerRequest : dto) {
            collection.add(toEntity(registerRequest));
        }

        return collection;
    }

    public Collection<UserResponse> toResponse(Collection<User> entity) {
        if (entity == null) {
            return null;
        }

        Collection<UserResponse> collection = new ArrayList<>(entity.size());
        for (User user : entity) {
            collection.add(toResponse(user));
        }

        return collection;
    }
}
