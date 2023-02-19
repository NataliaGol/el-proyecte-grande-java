package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.AnimalDto;
import com.codecool.dogmate.entity.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnimalMapper {

    @Mapping(source = "animalTypeId", target = "animalType.id")
    @Mapping(source = "breedId", target = "breed.id")
    @Mapping(source = "userId", target = "user.id")
    @Mapping(target = "gender", expression = "java(dto.getIsMale() ? Gender.MALE : Gender.FEMALE)")
    Animal toEntity(AnimalDto dto);

    @Mapping(source = "animalType.id", target = "animalTypeId")
    @Mapping(source = "breed.id", target = "breedId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "gender.male", target = "isMale")
    AnimalDto toDto(Animal entity);

    default List<AnimalDto> toDto(List<Animal> entities) {
        return entities
                .stream()
                .map(this::toDto)
                .toList();
    }

}
