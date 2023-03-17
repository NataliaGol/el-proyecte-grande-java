package com.codecool.dogmate.dto.animaltype;

import com.codecool.dogmate.dto.breed.BreedDto;

import java.util.List;

public record AnimalTypeDto(
        Integer id,
        String name,
        String description,
        List<BreedDto>breeds
) {
}
