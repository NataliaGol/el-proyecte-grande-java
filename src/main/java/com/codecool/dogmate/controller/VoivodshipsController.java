package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.voivodship.VoivodeshipDto;
import com.codecool.dogmate.service.VoivodshipsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/voivodships")
public class VoivodshipsController {

    private final VoivodshipsService voivodshipsService;

    public VoivodshipsController(VoivodshipsService voivodshipsService) {
        this.voivodshipsService = voivodshipsService;
    }

    @GetMapping
    public List<VoivodeshipDto> getAllVoivodeship() {return voivodshipsService.getVoivodeships();

    }

}
