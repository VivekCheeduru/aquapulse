package com.aquapulse.sensorsimulator.dto;

import com.aquapulse.sensorsimulator.models.SensorStatus;
import com.aquapulse.sensorsimulator.models.SensorType;

import java.time.Instant;

public class VirtualSensorResponseDto {
    private String sensorId;
    private SensorType type;
    private String location;
    private Double minValue;
    private Double maxValue;
    private Long reportingInterval;
    private SensorStatus status;
    private Instant createdAt;
    private Instant updatedAt;
}
