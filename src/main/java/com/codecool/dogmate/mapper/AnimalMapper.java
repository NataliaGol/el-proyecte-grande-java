package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.animal.AnimalDto;
import com.codecool.dogmate.entity.Animal;
import com.codecool.dogmate.entity.AnimalType;
import com.codecool.dogmate.entity.Breed;
import com.codecool.dogmate.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnimalMapper {

    public Animal toEntity(AnimalDto dto) {
        if (dto == null) {
            return null;
        }

        Animal animal = new Animal();

        animal.setAnimalType(animalDtoToAnimalType(dto));
        animal.setBreed(animalDtoToBreed(dto));
        animal.setUser(animalDtoToUser(dto));
        animal.setId(dto.getId());
        animal.setName(dto.getName());
        animal.setBirthYear(dto.getBirthYear());
        animal.setPictureLocation(dto.getPictureLocation());
        animal.setDescription(dto.getDescription());

        animal.setGender(dto.getIsMale() ? Gender.MALE : Gender.FEMALE);

        return animal;
    }

    public AnimalDto toDto(Animal entity) {
        if (entity == null) {
            return null;
        }

        AnimalDto animalDto = new AnimalDto();

        animalDto.setAnimalTypeId(entityAnimalTypeId(entity));
        animalDto.setBreedId(entityBreedId(entity));
        animalDto.setUserId(entityUserId(entity));
        animalDto.setIsMale(entityGenderMale(entity));
        animalDto.setId(entity.getId());
        animalDto.setName(entity.getName());
        animalDto.setBirthYear(entity.getBirthYear());
        animalDto.setPictureLocation(entity.getPictureLocation());
        animalDto.setDescription(entity.getDescription());

        return animalDto;
    }

    public List<AnimalDto> toDto(List<Animal> entities) {
        return entities
                .stream()
                .map(this::toDto)
                .toList();
    }

    protected AnimalType animalDtoToAnimalType(AnimalDto animalDto) {
        if (animalDto == null) {
            return null;
        }

        AnimalType animalType = new AnimalType();

        animalType.setId(animalDto.getAnimalTypeId());

        return animalType;
    }

    protected Breed animalDtoToBreed(AnimalDto animalDto) {
        if (animalDto == null) {
            return null;
        }

        Breed breed = new Breed();

        breed.setId(animalDto.getBreedId());

        return breed;
    }

    protected User animalDtoToUser(AnimalDto animalDto) {
        if (animalDto == null) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id(animalDto.getUserId());

        return user.build();
    }

    private Integer entityAnimalTypeId(Animal animal) {
        if (animal == null) {
            return null;
        }
        AnimalType animalType = animal.getAnimalType();
        if (animalType == null) {
            return null;
        }
        return animalType.getId();
    }

    private Integer entityBreedId(Animal animal) {
        if (animal == null) {
            return null;
        }
        Breed breed = animal.getBreed();
        if (breed == null) {
            return null;
        }
        return breed.getId();
    }

    private Integer entityUserId(Animal animal) {
        if (animal == null) {
            return null;
        }
        User user = animal.getUser();
        if (user == null) {
            return null;
        }
        return user.getId();
    }

    private Boolean entityGenderMale(Animal animal) {
        if (animal == null) {
            return null;
        }
        Gender gender = animal.getGender();
        if (gender == null) {
            return null;
        }
        return gender.getMale();
    }
}
