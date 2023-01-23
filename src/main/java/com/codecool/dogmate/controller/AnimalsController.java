package com.codecool.dogmate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/animals")
public class AnimalsController {

    @GetMapping
    public String AnimalsPage() {

        return "animals";
    }
}
