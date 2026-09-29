package com.aquapulse.sensorsimulator.services;

import com.aquapulse.sensorsimulator.config.SensorConfiguration;
import com.aquapulse.sensorsimulator.config.SensorProperties;
import com.aquapulse.sensorsimulator.dto.VirtualSensorResponseDto;
import com.aquapulse.sensorsimulator.factory.SensorFactory;
import com.aquapulse.sensorsimulator.models.SensorType;
import com.aquapulse.sensorsimulator.models.TelemetryEvent;
import com.aquapulse.sensorsimulator.models.VirtualSensor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestClient;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class SensorSimulatorService {
    private final SensorFactory sensorFactory;
    private final SensorProperties sensorProperties;
    private final RestClient restClient;
    private  List<VirtualSensor> sensors;
    public SensorSimulatorService(SensorFactory sensorFactory, SensorProperties sensorProperties, RestClient restClient){
        this.sensorFactory=sensorFactory;
        this.sensorProperties=sensorProperties;
        this.restClient = restClient;
    }
    public void initalizeSensors(){
        sensors=new ArrayList<>();
        for(SensorConfiguration configuration:sensorProperties.getSensors()){
            VirtualSensor sensor=sensorFactory.createSensor(configuration);
            sensors.add(sensor);
        }
    }
    public List<VirtualSensor> getSensors(){
        return sensors;
    }
    public TelemetryEvent generateTelemetry(VirtualSensor sensor){
        double value=sensor.generateNextValue();
        return new TelemetryEvent(sensor.getSensorId(),
                sensor.getType(),
                sensor.getLocation(),
                Instant.now(),
                value,
                getUnit(sensor.getType()) );
    }
    public String getUnit(SensorType type){
        return switch (type){
            case WATER_LEVEL -> "meters";
            case PRESSURE -> "Pascal";
            case FLOW_RATE -> "GPM";
            case TEMPERATURE -> "Celsius";
            case RAINFALL -> "Centimeters";
        };
    }

    public TelemetryEvent sendTelemetry(TelemetryEvent event){
        return restClient.post().
                uri("/telemetry").
                body(event).retrieve().
                body(TelemetryEvent.class);
    }
    public String recieveTest(){
        return restClient.get().uri("telemetry/test").retrieve().body(String.class);
    }
    public VirtualSensorResponseDto registerSensor(VirtualSensor sensor){
        return restClient.post().uri("api/sensors").body(sensor).retrieve().body(VirtualSensorResponseDto.class);
    }
}
