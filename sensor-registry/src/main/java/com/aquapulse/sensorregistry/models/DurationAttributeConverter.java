package com.aquapulse.sensorregistry.models;

import jakarta.persistence.AttributeConverter;

import java.time.Duration;

public class DurationAttributeConverter implements AttributeConverter<Duration,Long> {

    @Override
    public Long convertToDatabaseColumn(Duration duration) {
        if(duration==null)
            return null;
        return duration.getSeconds();
    }

    @Override
    public Duration convertToEntityAttribute(Long seconds) {
        if(seconds==null)
            return null;
        return Duration.ofSeconds(seconds);
    }
}
