package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.animal.AnimalDto;
import com.codecool.dogmate.dto.animal.NewAnimalDto;
import com.codecool.dogmate.entity.Animal;
import com.codecool.dogmate.entity.AnimalType;
import com.codecool.dogmate.entity.Breed;
import com.codecool.dogmate.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AnimalMapper {


    public Animal mapNewAniamlDtoToEntity(NewAnimalDto dto, AnimalType animalType, Breed breed, User user) {
        return new Animal(
                dto.name(),
                animalType,
                breed,
                user,
                dto.birthYear(),
                dto.pictureLocation(),
                dto.description(),
                dto.gender()
        );
    }

    public AnimalDto mapEntityToAnimalDto(Animal entity) {
        return new AnimalDto(
                entity.getId(),
                entity.getName(),
                entity.getAnimalType().getId(),
                entity.getBreed().getId(),
                entity.getUser().getId(),
                entity.getBirthYear(),
                entity.getPictureLocation(),
                entity.getDescription(),
                entity.getGender(),
                entity.getDate_create(),
                entity.getDate_modify(),
                entity.getDate_archive(),
                entity.getArchive()
        );
    }
}
