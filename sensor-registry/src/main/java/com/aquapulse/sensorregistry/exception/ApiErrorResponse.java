package com.aquapulse.sensorregistry.exception;

import com.aquapulse.sensorregistry.models.SensorStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@Getter
public class ApiErrorResponse {
    private Instant timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
