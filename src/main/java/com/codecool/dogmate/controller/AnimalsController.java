package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.AnimalDto;
import com.codecool.dogmate.service.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalsController {

    private final AnimalService animalService;

    public AnimalsController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    List<AnimalDto> getAllDogs() {
        return animalService.findAllDogs();
    }

    @PostMapping
    ResponseEntity<HttpStatus> addDog(@RequestBody AnimalDto dto) {
        animalService.addDog(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
