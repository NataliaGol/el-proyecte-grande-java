package com.codecool.dogmate.dto.city;

import com.codecool.dogmate.entity.Province;
import com.codecool.dogmate.entity.Voivodeship;

import java.util.TimeZone;

public record NewCityDto(
    String name,
    Province province
){
}
