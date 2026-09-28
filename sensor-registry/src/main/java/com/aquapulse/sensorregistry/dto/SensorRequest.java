package com.aquapulse.sensorregistry.dto;

import com.aquapulse.sensorregistry.models.SensorType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
public class SensorRequest {
    @NotBlank
    private String sensorId;
    @NotNull
    private SensorType type;
    @NotNull
    private String location;
    @NotNull
    private Double minValue;
    @NotNull
    private Double maxValue;
    @Positive
    @NotNull
    private Long reportingIntervalSeconds;
}
