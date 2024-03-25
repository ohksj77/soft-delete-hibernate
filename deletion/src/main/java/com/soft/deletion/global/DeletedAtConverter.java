package com.soft.deletion.global;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.LocalDateTime;
import java.util.Optional;

@Converter
public class DeletedAtConverter implements AttributeConverter<Boolean, LocalDateTime> {

    @Override
    public LocalDateTime convertToDatabaseColumn(final Boolean attribute) {
        return LocalDateTime.now();
    }

    @Override
    public Boolean convertToEntityAttribute(final LocalDateTime dbData) {
        return Optional.ofNullable(dbData).isPresent();
    }
}
