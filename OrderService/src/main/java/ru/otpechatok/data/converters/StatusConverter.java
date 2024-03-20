package ru.otpechatok.data.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ru.otpechatok.data.Status;

import java.util.Arrays;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {
    @Override
    public String convertToDatabaseColumn(Status attribute) {
        if (attribute!=null){
            return attribute.getStatus();
        }
        return null;
    }

    @Override
    public Status convertToEntityAttribute(String dbData) {
        if (dbData==null){
            return null;
        }
        return Arrays.stream(Status.values())
                .filter(x->x.getStatus().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}
