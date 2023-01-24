package com.codecool.dogmate.mapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GenderConverter implements AttributeConverter<Gender, Boolean> {

    @Override
    public Boolean convertToDatabaseColumn(Gender gender) {
        return gender.getMale();
    }

    @Override
    public Gender convertToEntityAttribute(Boolean isMale) {
        return Boolean.TRUE.equals(isMale) ? Gender.MALE : Gender.FEMALE;
    }

}
