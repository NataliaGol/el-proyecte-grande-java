package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.UserRequest;
import com.codecool.dogmate.dto.UserResponse;
import com.codecool.dogmate.entity.User;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

@Mapper(componentModel = "spring") // @Component is added to generated class
public abstract class UserMapper {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Named("toEntity")
    @Mapping(target = "password", expression = "java(passwordEncoder.encode(dto.getPassword()))")
    public abstract User toEntity(UserRequest dto);

    @Named("toResponse")
    public abstract UserResponse toResponse(User entity);

    @IterableMapping(qualifiedByName = "toEntity")
    public abstract Collection<User> toEntity(Collection<UserRequest> dto);

    @IterableMapping(qualifiedByName = "toResponse")
    public abstract Collection<UserResponse> toResponse(Collection<User> entity);

}
