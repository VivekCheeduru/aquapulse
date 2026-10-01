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
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class SimulatorStartup implements CommandLineRunner {
    private final SensorSimulatorService simulatorService;
    public SimulatorStartup(SensorSimulatorService simulatorService){
        this.simulatorService=simulatorService;
    }
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Initialize sensors from the Configuration YAML file....");
        Optional<List<VirtualSensor>> sensorsOptional= Optional.ofNullable(simulatorService.getSensors());
        System.out.println(sensorsOptional.isPresent());
        if(!sensorsOptional.isPresent()){
            System.out.println("simulator service not intialized intially so loading sensors into Memory..");
            simulatorService.initalizeSensors();
            List<VirtualSensor> sensors=simulatorService.getSensors();
            System.out.println("Completed Intializing Sensors");
            //Registering sensors with sensor-registry
            System.out.println(sensors+"Initiating for sensor registry...");
            for(VirtualSensor sensor:sensors){
                simulatorService.registerSensor(sensor);
            }

            System.out.println("Posting the TelemetryEvent Data from sensor-simulator to sensor-registry");

        ScheduledExecutorService schedulor= Executors.newScheduledThreadPool(5);
        for (VirtualSensor sensor : sensors) {
//            System.out.println("Testing RestClient"+simulatorService.recieveTest());
            System.out.println("POSTED TELEMETRY EVENT TO SENSOR-REGISTRY :" +
                    schedulor.scheduleAtFixedRate(()->{
                        simulatorService.sendTelemetry(simulatorService.generateTelemetry(sensor));
                            },
                            0,
                            1,
                            TimeUnit.MILLISECONDS
                            ));
                    }
        }
    }
}
