package com.aquapulse.sensorregistry.exception;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

public class SensorAlreadyExistsException extends RuntimeException{
    private String message;

    public SensorAlreadyExistsException(String message) {
        this.message=message;
    }
}
