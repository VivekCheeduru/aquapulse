package com.aquapulse.sensorsimulator.models;

import java.time.Instant;

public record TelemetryEvent(String sensorId,
                             SensorType sensorType,
                             String location,
                              Instant timestamp,
                             double value,
                             String unit){

}
