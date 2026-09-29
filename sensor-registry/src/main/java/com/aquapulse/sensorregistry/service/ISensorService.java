package com.aquapulse.sensorregistry.service;

import com.aquapulse.sensorregistry.dto.SensorRequest;
import com.aquapulse.sensorregistry.models.Sensor;

import java.util.List;

public interface ISensorService{
    Sensor createSensor(SensorRequest request);
    Sensor getSensorById(String sensorId);

    List<Sensor> getAllSensors();

    Sensor updateSensorById(SensorRequest request,String sensorId);
    void deleteSensorById(String sensorId);
}
