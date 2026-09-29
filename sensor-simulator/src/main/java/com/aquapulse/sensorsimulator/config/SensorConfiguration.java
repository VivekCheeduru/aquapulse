package com.aquapulse.sensorsimulator.config;

import com.aquapulse.sensorsimulator.models.SensorType;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
@Getter
@Setter
public class SensorConfiguration {
    private String id;
    private SensorType type;
    private String location;
    private Double minimumValue;
    private Double maximumValue;
    private Double initialValue;
    private Long reportingInterval;
}
