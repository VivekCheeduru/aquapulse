package com.aquapulse.sensorsimulator.services;

import com.aquapulse.sensorsimulator.dto.VirtualSensorResponseDto;
import com.aquapulse.sensorsimulator.models.SensorType;
import com.aquapulse.sensorsimulator.models.TelemetryEvent;
import com.aquapulse.sensorsimulator.models.VirtualSensor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
public class SimulatorStartup implements CommandLineRunner {
    private final SensorSimulatorService simulatorService;
    public SimulatorStartup(SensorSimulatorService simulatorService){
        this.simulatorService=simulatorService;
    }
    @Override
    public void run(String... args) throws Exception {
        //Initialize sensors from the Configuration YAML file....
        simulatorService.initalizeSensors();
        System.out.println("Completed Intializing Sensors");
        //Registering sensors with sensor-registry
        List<VirtualSensor> sensors=simulatorService.getSensors();
        System.out.println(sensors+"Initiating for sensor registry...");
        for(VirtualSensor sensor:sensors){
            simulatorService.registerSensor(sensor);
        }
        //        List<VirtualSensor> sensors=simulatorService.getSensors();
        for (VirtualSensor sensor : sensors) {
//            System.out.println("Testing RestClient"+simulatorService.recieveTest());
            System.out.println("POSTED TELEMETRY EVENT TO REGISTER :" +
                    simulatorService.sendTelemetry(simulatorService.generateTelemetry(sensor)));
        }


    }
}
