package com.aquapulse.sensorsimulator.generator;

import com.aquapulse.sensorsimulator.models.TelemetryEvent;
import com.aquapulse.sensorsimulator.models.VirtualSensor;

import java.time.Instant;

public class TelemetryGenerator {
    private final VirtualSensor sensor;
    public TelemetryGenerator(VirtualSensor sensor){
        this.sensor=sensor;
    }
    public TelemetryEvent generate(){
        double value=sensor.generateNextValue();
        return new TelemetryEvent(sensor.getSensorId(),
                    sensor.getType(),sensor.getLocation(),
                Instant.now(),sensor.getCurrValue(),
                "MTRS");

    }
}
