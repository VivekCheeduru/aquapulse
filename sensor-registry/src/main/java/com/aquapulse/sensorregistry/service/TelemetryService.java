package com.aquapulse.sensorregistry.service;

import com.aquapulse.sensorregistry.exception.ApiErrorResponse;
import com.aquapulse.sensorregistry.exception.SensorNotFoundException;
import com.aquapulse.sensorregistry.models.Sensor;
import com.aquapulse.sensorregistry.models.TelemetryEvent;
import com.aquapulse.sensorregistry.repository.SensorRepository;
import com.aquapulse.sensorregistry.repository.TelemetryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class TelemetryService {
    @Autowired
    private ModelMapper modelMapper;
    private final TelemetryRepository telemetryRepository;
    private final SensorRepository sensorRepository;

    public TelemetryService(TelemetryRepository telemetryRepository, SensorRepository sensorRepository) {
        this.telemetryRepository = telemetryRepository;
        this.sensorRepository = sensorRepository;
    }

    public TelemetryEvent saveTelemetry(TelemetryEvent event)  {
        Sensor sensor=sensorRepository.findById(event.getSensorId()).orElseThrow(
                ()->new SensorNotFoundException("INVALID TELEMETRY EVENT..."));
        event.setSensor(sensor);
        return telemetryRepository.save(event);
    }
}
