package com.aquapulse.sensorregistry.dto;

import com.aquapulse.sensorregistry.models.SensorType;

import java.time.Instant;

public record RequestTelemetryEventDto(String sensorId,
                                       SensorType sensorType,
                                       String location,
                                       Instant timestamp,
                                       double value,
                                       String unit){

}