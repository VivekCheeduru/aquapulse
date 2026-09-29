package com.aquapulse.sensorsimulator.models;

import java.time.Duration;

public record SensorClientDto(String sensorId,
                              SensorType type,
                              String location,
                              Double minimumValue,
                              Double maximumValue,
                              Double currValue,
                              Duration reportingInterval) {
}
