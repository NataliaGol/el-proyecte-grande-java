package com.codecool.dogmate.service;

import com.codecool.dogmate.dto.AnimalDto;
import com.codecool.dogmate.mapper.AnimalMapper;
import com.codecool.dogmate.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;

    public AnimalService(AnimalRepository animalRepository, AnimalMapper animalMapper) {
        this.animalRepository = animalRepository;
        this.animalMapper = animalMapper;
    }

    public void addDog(AnimalDto dto) {
        animalRepository.insertDog(dto);
    }

    public List<AnimalDto> findAllDogs() {
        return animalMapper.toDto(animalRepository.findAll());
    }
}
