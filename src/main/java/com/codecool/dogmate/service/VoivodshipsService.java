package com.codecool.dogmate.service;

import com.codecool.dogmate.dto.voivodship.VoivodeshipDto;
import com.codecool.dogmate.mapper.VoivodshipsMapper;
import com.codecool.dogmate.repository.VoivodshipsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoivodshipsService {
    private final VoivodshipsRepository voivodshipsRepository;
    private final VoivodshipsMapper voivodshipsMapper;

    public VoivodshipsService(VoivodshipsRepository voivodshipsRepository, VoivodshipsMapper voivodshipsMapper) {
        this.voivodshipsRepository = voivodshipsRepository;
        this.voivodshipsMapper = voivodshipsMapper;
    }

    public List<VoivodeshipDto> getVoivodeships() {
        return voivodshipsRepository.findAllBy().stream()
                .map(voivodshipsMapper::mapEntityToVoivodeshipDto)
                .toList();
    }
}
