package com.aquapulse.sensorsimulator.services;

import com.aquapulse.sensorsimulator.models.VirtualSensor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SimulatorStartup implements CommandLineRunner {
    private final SensorSimulatorService simulatorService;
    public SimulatorStartup(SensorSimulatorService simulatorService){
        this.simulatorService=simulatorService;
    }
    @Override
    public void run(String... args) throws Exception {
        simulatorService.initalizeSensors();
        List<VirtualSensor> sensors=simulatorService.getSensors();
        for(VirtualSensor sensor:sensors){
            System.out.println(simulatorService.generateTelemetry(sensor));
        }
    }
}
