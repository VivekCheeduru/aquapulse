package com.aquapulse.sensorregistry.service;

import com.aquapulse.sensorregistry.dto.SensorRequest;
import com.aquapulse.sensorregistry.exception.SensorAlreadyExistsException;
import com.aquapulse.sensorregistry.exception.SensorNotFoundException;
import com.aquapulse.sensorregistry.models.Sensor;
import com.aquapulse.sensorregistry.models.SensorStatus;
import com.aquapulse.sensorregistry.repository.SensorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class SensorService implements ISensorService{
    private final SensorRepository sensorRepository;
    public SensorService(SensorRepository sensorRepository){
        this.sensorRepository=sensorRepository;
    }
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Sensor createSensor(SensorRequest request){
        Optional<Sensor> sensorOptional=sensorRepository.findById(request.getSensorId());
        System.out.println("Checking for sensor ID if already exists or not?"+sensorOptional.isPresent());
        if(sensorOptional.isPresent()){
            throw new SensorAlreadyExistsException("Sensor with ID already exists...");
        }
        Sensor sensor=new Sensor();
        sensor.setSensorId(request.getSensorId());
        sensor.setType(request.getType());
        sensor.setLocation(request.getLocation());
        sensor.setMinimumValue(request.getMinimumValue());
        sensor.setMaximumValue(request.getMaximumValue());
        sensor.setStatus(SensorStatus.ACTIVE);
        sensor.setReportingInterval(Duration.ofSeconds(request.getReportingInterval()));
        sensor.setCreatedAt(Instant.now());
        sensor.setUpdatedAt(Instant.now());
        return sensorRepository.save(sensor);
    }

    @Override
    public Sensor getSensorById(String sensorId) {
        return sensorRepository.findById(sensorId).orElseThrow(()-> new SensorNotFoundException("Invalid sensorId"));
    }

    @Override
    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    @Override
    public Sensor updateSensorById(SensorRequest request, String sensorId) {
        Sensor oldSensor=sensorRepository.findById(sensorId).orElseThrow(()->new SensorNotFoundException("Update Failed: No matching sensorId found to Update"));
        Sensor newSensor=modelMapper.map(request,Sensor.class);
        newSensor.setStatus(SensorStatus.ACTIVE);
        newSensor.setCreatedAt(oldSensor.getCreatedAt());
        newSensor.setUpdatedAt(Instant.now());
        newSensor.setReportingInterval(Duration.ofSeconds(request.getReportingInterval()));
        return sensorRepository.save(newSensor);
    }

    @Override
    public void deleteSensorById(String sensorId) {
        sensorRepository.deleteById(sensorId);
    }



}
