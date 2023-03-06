package com.codecool.dogmate.dto.province;

import com.codecool.dogmate.dto.city.CityDto;

import java.util.List;
import java.util.TimeZone;

public record ProvinceDto(
        Integer id,
        String terytId,
        String name,
        TimeZone date_create,
        TimeZone date_modify,
        TimeZone date_archive,
        Boolean archive,
        List<CityDto> cities

){
}
