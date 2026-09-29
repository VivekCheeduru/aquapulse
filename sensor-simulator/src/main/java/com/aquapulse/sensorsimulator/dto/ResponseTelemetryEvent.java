package com.aquapulse.sensorsimulator.dto;

import com.aquapulse.sensorsimulator.models.SensorType;

import java.time.Instant;
import java.util.UUID;

public record ResponseTelemetryEvent(UUID id,
                                     String sensorId,
                                     SensorType sensorType,
                                     String location,
                                     Instant timestamp,
                                     double value,
                                     String unit) {
}
