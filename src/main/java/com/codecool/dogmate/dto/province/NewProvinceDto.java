package com.codecool.dogmate.dto.province;

import com.codecool.dogmate.entity.Voivodeship;

import java.util.TimeZone;

public record NewProvinceDto(
        String terytId,
        String name,
        Voivodeship voivodship
){
}
