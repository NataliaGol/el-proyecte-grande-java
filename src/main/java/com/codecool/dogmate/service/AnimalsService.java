package com.codecool.dogmate.service;

import com.codecool.dogmate.dto.animal.AnimalDto;
import com.codecool.dogmate.dto.animal.NewAnimalDto;
import com.codecool.dogmate.entity.Animal;
import com.codecool.dogmate.excpetion.AnimalNotFoudException;
import com.codecool.dogmate.mapper.AnimalMapper;
import com.codecool.dogmate.repository.AnimalRepository;
import com.codecool.dogmate.repository.AnimalTypeRepository;
import com.codecool.dogmate.repository.BreedRepository;
import com.codecool.dogmate.repository.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalsService {

    private final AnimalRepository animalRepository;
    private final AnimalTypeRepository animalTypeRepository;
    private final BreedRepository breedRepository;
    private final UserRepository userRepository;

    private final AnimalMapper animalMapper;

    public AnimalsService(AnimalRepository animalRepository, AnimalTypeRepository animalTypeRepository, BreedRepository breedRepository, UserRepository userRepository, AnimalMapper animalMapper) {
        this.animalRepository = animalRepository;
        this.animalTypeRepository = animalTypeRepository;
        this.breedRepository = breedRepository;
        this.userRepository = userRepository;
        this.animalMapper = animalMapper;
    }


    public List<AnimalDto> getAnimals(Pageable pageable) {
        return animalRepository.findAllBy().stream()
                .map(animalMapper::mapEntityToAnimalDto)
                .toList();
    }

    public List<AnimalDto> getAnimals() {
        return animalRepository.findAllBy().stream()
                .map(animalMapper::mapEntityToAnimalDto)
                .toList();
    }

    public AnimalDto getAnimalById(Integer id) {
        return animalRepository.findOneById(id)
                .map(animalMapper::mapEntityToAnimalDto)
                .orElseThrow(() -> new AnimalNotFoudException(id));
    }

    public AnimalDto createAnimal(NewAnimalDto animal) {
        Animal entity = animalMapper.mapNewAniamlDtoToEntity(
                animal,
                animalTypeRepository.findOneById(animal.animalTypesId()).get(),
                breedRepository.findOneById(animal.breedId()).get(),
                userRepository.findById(animal.userId()).get()
                );
        Animal savedEntity = animalRepository.save(entity);
        return animalMapper.mapEntityToAnimalDto(savedEntity);
    }
}
