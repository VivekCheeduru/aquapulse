package com.aquapulse.sensorregistry.dto;

import com.aquapulse.sensorregistry.models.SensorStatus;
import com.aquapulse.sensorregistry.models.SensorType;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class SensorResponse {
    private String sensorId;
    private SensorType type;
    private String location;
    private Double minimumValue;
    private Double maximumValue;
    private Long reportingInterval;
    private SensorStatus status;
    private Instant createdAt;
    private Instant updatedAt;
}
