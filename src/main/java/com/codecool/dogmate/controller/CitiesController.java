package com.codecool.dogmate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cities")
public class CitiesController {

    @GetMapping
    public String CitiesPage() {

        return "animals";
    }
}
