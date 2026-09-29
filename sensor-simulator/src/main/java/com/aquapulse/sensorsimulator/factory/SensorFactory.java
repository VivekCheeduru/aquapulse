package com.aquapulse.sensorsimulator.factory;

import com.aquapulse.sensorsimulator.config.SensorConfiguration;
import com.aquapulse.sensorsimulator.exceptions.FactoryFailureException;
import com.aquapulse.sensorsimulator.models.SensorType;
import com.aquapulse.sensorsimulator.models.VirtualSensor;
import org.springframework.stereotype.Component;

import java.time.Duration;
@Component
public class SensorFactory {

    public VirtualSensor createSensor(SensorConfiguration configuration){
           return new VirtualSensor(
                   configuration.getId(),
                   configuration.getType(),
                   configuration.getLocation(),
                   configuration.getMinimumValue(),
                   configuration.getMaximumValue(),
                   configuration.getInitialValue(),
                   configuration.getReportingInterval()
           );
    }
}
