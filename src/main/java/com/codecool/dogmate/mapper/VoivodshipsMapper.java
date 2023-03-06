package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.voivodship.NewVoivodeshipDto;
import com.codecool.dogmate.dto.voivodship.VoivodeshipDto;
import com.codecool.dogmate.entity.Voivodeship;
import org.springframework.stereotype.Component;

@Component
public class VoivodshipsMapper {

    private final ProvincesMapper provincesMapper;
    public VoivodshipsMapper(ProvincesMapper provincesMapper) {this.provincesMapper = provincesMapper; }

    public Voivodeship mapNewVoivodeshipDtoToEntity(NewVoivodeshipDto dto) {
        return new Voivodeship(dto.name(), dto.terytId());
    }
    public VoivodeshipDto mapEntityToVoivodeshipDto(Voivodeship entity) {
        return new VoivodeshipDto(
                entity.getId(),
                entity.getName(),
                entity.getTerytId(),
                entity.getDate_create(),
                entity.getDate_modify(),
                entity.getDate_archive(),
                entity.getArchive(),
                entity.getProvinces().stream()
                        .map(provincesMapper::mapEntityToProvinceDto)
                        .toList()
        );
    }
}
