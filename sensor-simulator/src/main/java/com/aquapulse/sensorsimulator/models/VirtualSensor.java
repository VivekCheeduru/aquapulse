package com.aquapulse.sensorsimulator.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Random;

@AllArgsConstructor
@Getter
@Setter
public class VirtualSensor {
    private final String sensorId;
    private final SensorType type;
    private final String location;
    private final Double minimumValue;
    private final Double maximumValue;
    private Double currValue;
    private final Long reportingInterval;

    public double generateNextValue(){
        Random random=new Random();
        double delta=-0.4*(0.8 * random.nextDouble());
        double nextValue=currValue+delta;
        nextValue=Math.max(minimumValue,Math.min(maximumValue,nextValue));
        currValue=nextValue;
        return currValue;
    }
}
