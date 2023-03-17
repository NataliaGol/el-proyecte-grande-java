package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.traininglevels.NewTrainingLevelDto;
import com.codecool.dogmate.dto.traininglevels.TrainingLevelDto;
import com.codecool.dogmate.dto.users.NewUserDto;
import com.codecool.dogmate.dto.users.UserDto;
import com.codecool.dogmate.entity.City;
import com.codecool.dogmate.entity.TrainingLevel;
import com.codecool.dogmate.entity.User;
import com.codecool.dogmate.entity.UserType;
import org.springframework.stereotype.Component;

@Component
public class TrainingLevelMapper {

    public TrainingLevel mapTrainingLevelDtoToEntity(NewTrainingLevelDto dto) {return new TrainingLevel(
            dto.name(),
            dto.description()
    );}

    public TrainingLevelDto mapEntityToTrainingLevelDto(TrainingLevel entity) {
        return new TrainingLevelDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription()
        );
    }
}
