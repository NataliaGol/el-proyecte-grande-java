package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.province.NewProvinceDto;
import com.codecool.dogmate.dto.province.ProvinceDto;
import com.codecool.dogmate.dto.voivodship.VoivodeshipDto;
import com.codecool.dogmate.entity.Province;
import com.codecool.dogmate.entity.Voivodeship;
import org.springframework.stereotype.Component;

@Component
public class ProvincesMapper {

    private final CitiesMapper citiesMapper;
    public ProvincesMapper(CitiesMapper citiesMapper) {this.citiesMapper = citiesMapper; }

    public Province mapNewProvinceDtoToEntity(NewProvinceDto dto) {
        return new Province(dto.name(), dto.terytId(), dto.voivodship());
    }

    public ProvinceDto mapEntityToProvinceDto(Province entity) {
        return new ProvinceDto(
                entity.getId(),
                entity.getName(),
                entity.getTerytId(),
                entity.getDate_create(),
                entity.getDate_modify(),
                entity.getDate_archive(),
                entity.getArchive(),
                entity.getCities().stream()
                        .map(citiesMapper::mapEntityToCityDto)
                        .toList()
        );
    }

}
