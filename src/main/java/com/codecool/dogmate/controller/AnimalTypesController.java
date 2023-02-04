package com.codecool.dogmate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/animal_types")
public class AnimalTypesController {

    @GetMapping
    public String AnimalTypesPage() {

        return "animals";
    }
}
