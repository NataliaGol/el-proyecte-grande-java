package com.codecool.dogmate.dto;

import com.codecool.dogmate.entity.Animal;
import com.codecool.dogmate.entity.UserType;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserDto {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String profilePictureLocation;
    private String avatarSmallLocation;
    private UserType userType;
    private String description;
    private Boolean isLocked;
    private Boolean isBanned;
    private OffsetDateTime createdAt;
    private Set<Animal> animals = new LinkedHashSet<>();
}
