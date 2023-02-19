package com.codecool.dogmate.dto;

import lombok.Data;

@Data
public class AnimalDto {

    private Integer id;
    private String name;
    private Integer animalTypeId;
    private Integer breedId;
    private Integer userId;
    private Integer birthYear;
    private String pictureLocation;
    private String description;
    private Boolean isMale;

}
