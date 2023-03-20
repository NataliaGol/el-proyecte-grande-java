package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.appuser.AppUserDto;
import com.codecool.dogmate.dto.appuser.NewAppUserDto;
import com.codecool.dogmate.entity.AppUser;
import com.codecool.dogmate.service.AppUserService;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appuser")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }


    @GetMapping
    public List<AppUserDto> getAllAppUsers() {return appUserService.getAppUsers();}

    @GetMapping(params = {"page", "size", "sort"})
    public List<AppUserDto> getAllUserWithPageable(Pageable pageable) {
        return appUserService.getAppUsers(pageable);
    }

    @GetMapping("/id/{id}")
    public AppUserDto getAppUserByUserId(@PathVariable Integer id) {
        return appUserService.getAppUserById(id);
    }

    @GetMapping("/email/{email}")
    public AppUserDto getAppUserByEmail(@PathVariable String email) {
        return appUserService.getAppUserByEmail(email);
    }

    @GetMapping("/name/{name}")
    public List<AppUserDto> getAppUserByName(@PathVariable String name) {
        return appUserService.getAppUserByName(name);
    }

    @PostMapping("/register")
    public AppUserDto newAppUser(@RequestBody NewAppUserDto newAppUserDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newAppUserDto.password());
        newAppUserDto.setPassword(encodedPassword);
        appUserService.createAppUser(newAppUserDto);
        return appUserService.getAppUserByEmail(newAppUserDto.email());
    }

}

