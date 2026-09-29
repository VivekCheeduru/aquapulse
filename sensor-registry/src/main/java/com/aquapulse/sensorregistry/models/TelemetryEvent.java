package com.aquapulse.sensorregistry.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "telemetry_event")
@Getter
@Setter
public class TelemetryEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(updatable = false,nullable = false)
    private String sensorId;
    @NotNull
    private SensorType type;
    @NotNull
    private String location;
    @NotNull
    private Instant timestamp;
    @NotNull
    private double value;
    @NotNull
    private String unit;
    @ManyToOne
    private Sensor sensor;

}
