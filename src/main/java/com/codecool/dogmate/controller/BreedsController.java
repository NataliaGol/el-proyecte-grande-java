package com.codecool.dogmate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/breeds")
public class BreedsController {

    @GetMapping
    public String BreedsPage() {

        return "animals";
    }
}
