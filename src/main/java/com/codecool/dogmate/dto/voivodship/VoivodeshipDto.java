package com.codecool.dogmate.dto.voivodship;

import com.codecool.dogmate.dto.province.ProvinceDto;

import java.util.List;
import java.util.TimeZone;

public record VoivodeshipDto(

    Integer id,
    String terytId,
    String name,
    TimeZone date_create,
    TimeZone date_modify,
    TimeZone date_archive,
    Boolean archive,
    List<ProvinceDto> provinces
) {
}
