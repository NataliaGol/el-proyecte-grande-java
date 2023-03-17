package com.codecool.dogmate.service;

import com.codecool.dogmate.dto.animaltype.AnimalTypeDto;
import com.codecool.dogmate.dto.animaltype.NewAnimalTypeDto;
import com.codecool.dogmate.entity.AnimalType;
import com.codecool.dogmate.excpetion.AnimalTypeNotFoudException;
import com.codecool.dogmate.mapper.AnimalTypeMapper;
import com.codecool.dogmate.repository.AnimalTypeRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AnimalTypesService {

    private final AnimalTypeRepository animalTypeRepository;
    private final AnimalTypeMapper animalTypeMapper;

    public AnimalTypesService(AnimalTypeRepository animalTypeRepository, AnimalTypeMapper animalTypeMapper) {
        this.animalTypeRepository = animalTypeRepository;
        this.animalTypeMapper = animalTypeMapper;
    }


    public List<AnimalTypeDto> getAnimalType() {
        return animalTypeRepository.findAllBy().stream()
                .map(animalTypeMapper::mapEntityToAnimalTypeDto)
                .toList();
    }

    public List<AnimalTypeDto> getAnimalType(Pageable pageable) {
        return animalTypeRepository.findAllBy(pageable).stream()
                .map(animalTypeMapper::mapEntityToAnimalTypeDto)
                .toList();
    }

    public AnimalTypeDto getAnimalTypeById(Integer id) {
        return animalTypeRepository.findOneById(id)
                .map(animalTypeMapper::mapEntityToAnimalTypeDto)
                .orElseThrow(() -> new AnimalTypeNotFoudException(id));
    }

    public AnimalTypeDto createAnimalType(NewAnimalTypeDto animalType) {
        AnimalType entity = animalTypeMapper.mapNewAnimalTypeDtoToEntity(animalType);
        AnimalType savedEntity = animalTypeRepository.save(entity);
        return animalTypeMapper.mapEntityToAnimalTypeDto(savedEntity);
    }
}
