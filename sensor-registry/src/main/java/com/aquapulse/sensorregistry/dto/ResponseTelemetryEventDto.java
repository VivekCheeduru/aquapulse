package com.aquapulse.sensorregistry.dto;

import com.aquapulse.sensorregistry.models.SensorType;

import java.time.Instant;
import java.util.UUID;

public record ResponseTelemetryEventDto (UUID id,
                                         String sensorId,
                                         SensorType sensorType,
                                         String location,
                                         Instant timestamp,
                                         double value,
                                         String unit){
}
