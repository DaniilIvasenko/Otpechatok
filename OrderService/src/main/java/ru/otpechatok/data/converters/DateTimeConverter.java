package ru.otpechatok.data.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * конвертер для преобразования даты LocalDateTime в Timestamp для сохранения в БД и чтения из нее.
 */
@Converter(autoApply = true)
public class DateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime locDateTime) {
        return locDateTime == null ? null : Timestamp.valueOf(locDateTime);
    }


    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp sqlTimestamp) {
        return sqlTimestamp == null ? null : sqlTimestamp.toLocalDateTime();
    }
}
