package com.aquapulse.sensorregistry.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name="sensor")
@Getter
@Setter
public class Sensor {
    @Id
    @Column(nullable = false,updatable = false,length = 50)
    private String sensorId;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SensorType type;
    @Column(nullable = false,length = 100)
    private String location;
    @NotNull
    @DecimalMin(value = "0.0")
    private double minimumValue;
    @NotNull
    private double maximumValue;
    @Convert(converter = DurationAttributeConverter.class)
    @Column(nullable = false)
    private Duration reportingInterval;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SensorStatus status;
    private Instant createdAt;
    private Instant updatedAt;
    @OneToMany(mappedBy ="sensor")
    private List<TelemetryEvent> telemetryEvents;

}
