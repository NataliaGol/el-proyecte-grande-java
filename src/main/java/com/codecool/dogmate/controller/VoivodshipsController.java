package com.codecool.dogmate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/voivodships")
public class VoivodshipsController {

    @GetMapping
    public String VoivodshipsPage() {

        return "animals";
    }
}
