package com.codecool.dogmate.dto.city;

import java.util.TimeZone;

public record CityDto(
        Integer id,
        String name,
        TimeZone date_create,
        TimeZone date_modify,
        TimeZone date_archive,
        Boolean archive
) {
}
