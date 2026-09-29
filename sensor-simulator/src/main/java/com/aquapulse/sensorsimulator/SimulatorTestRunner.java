package com.aquapulse.sensorsimulator;

import com.aquapulse.sensorsimulator.generator.TelemetryGenerator;
import com.aquapulse.sensorsimulator.models.SensorType;
import com.aquapulse.sensorsimulator.models.VirtualSensor;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimulatorTestRunner {
    public static void main(String[] args) {
        VirtualSensor sensor=new VirtualSensor(
                "SENSOR-1001",
                SensorType.WATER_LEVEL,
                "RESERVIOR-A",
                60.0,
                90.1,
                82.4,
                1L
        );
        TelemetryGenerator generator=new TelemetryGenerator(sensor);
       ScheduledExecutorService scheduler=Executors.newScheduledThreadPool(1);
           Future<?> scheduledFuture = scheduler.scheduleAtFixedRate(
                   ()->{
                       System.out.println(generator.generate());
                   },
                   0,
                   sensor.getReportingInterval(),
                   TimeUnit.MILLISECONDS
           );
    }
}
