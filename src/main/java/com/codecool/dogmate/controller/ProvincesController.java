package com.codecool.dogmate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/provinces")
public class ProvincesController {

    @GetMapping
    public String ProvincesPage() {

        return "animals";
    }
}
