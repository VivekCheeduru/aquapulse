package com.aquapulse.sensorregistry.controller;

import com.aquapulse.sensorregistry.dto.SensorRequest;
import com.aquapulse.sensorregistry.dto.SensorResponse;
import com.aquapulse.sensorregistry.models.Sensor;
import com.aquapulse.sensorregistry.service.ISensorService;
import com.aquapulse.sensorregistry.service.SensorService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/sensors")
public class SensorController {
    private final ISensorService sensorService;
    @Autowired
    private ModelMapper modelMapper;
    public SensorController(ISensorService sensorService){
        this.sensorService=sensorService;
    }

    @PostMapping
    public ResponseEntity<SensorResponse> createSensor(@RequestBody SensorRequest request){
        Sensor sensor=sensorService.createSensor(request);
        SensorResponse response=new SensorResponse();
        response.setSensorId(sensor.getSensorId());
        response.setType(sensor.getType());
        response.setLocation(sensor.getLocation());
        response.setStatus(sensor.getStatus());
        response.setReportingInterval(sensor.getReportingInterval().getSeconds());
        response.setCreatedAt(Instant.now());
        response.setUpdatedAt(Instant.now());
        response.setMaximumValue(sensor.getMaximumValue());
        response.setMinimumValue(sensor.getMinimumValue());
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{sensorId}")
    public ResponseEntity<SensorResponse> getSensorById(@PathVariable String sensorId){
        Sensor sensor=sensorService.getSensorById(sensorId);
        SensorResponse response=modelMapper.map(sensor,SensorResponse.class);
        response.setReportingInterval(sensor.getReportingInterval().getSeconds());
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }
    @GetMapping
    public ResponseEntity<List<SensorResponse>> getAllSensors(){
        List<Sensor> sensorList=sensorService.getAllSensors();
        List<SensorResponse> responseList=new ArrayList<>();
        for(Sensor sensor:sensorList){
            SensorResponse response=modelMapper.map(sensor,SensorResponse.class);
            response.setReportingInterval(sensor.getReportingInterval().getSeconds());
            responseList.add(response);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(responseList);
    }
    @PutMapping("/{sensorId}")
    public ResponseEntity<SensorResponse> updateSensorById(@RequestBody @Valid SensorRequest request, @PathVariable String sensorId){
        Sensor sensor=sensorService.updateSensorById(request,sensorId);
        SensorResponse response=modelMapper.map(sensor,SensorResponse.class);
        response.setReportingInterval(sensor.getReportingInterval().getSeconds());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
    @DeleteMapping("/{sensorId}")
    public ResponseEntity<Void> deleteSensorById(@PathVariable String sensorId){
       sensorService.deleteSensorById(sensorId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
