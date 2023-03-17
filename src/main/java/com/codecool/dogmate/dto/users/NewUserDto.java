package com.codecool.dogmate.dto.users;


import com.codecool.dogmate.entity.Animal;

import java.util.List;

public record NewUserDto(
    String name,
    String email,
    String password,
    String profilePictureLocation,
    String avatarSmallLocation,
    Integer userType,
    Integer city,
    String description,
    List<Animal> animals
){
}
