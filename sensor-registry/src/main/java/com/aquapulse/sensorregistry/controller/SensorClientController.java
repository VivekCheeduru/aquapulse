package com.aquapulse.sensorregistry.controller;

import com.aquapulse.sensorregistry.dto.RequestTelemetryEventDto;
import com.aquapulse.sensorregistry.dto.ResponseTelemetryEventDto;
import com.aquapulse.sensorregistry.models.TelemetryEvent;
import com.aquapulse.sensorregistry.service.TelemetryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/telemetry")
public class SensorClientController {
    private final TelemetryService telemetryService;

    public SensorClientController(TelemetryService telemetryService) {
        this.telemetryService = telemetryService;
    }

    @PostMapping
    public ResponseTelemetryEventDto recieveTelemetry(@RequestBody RequestTelemetryEventDto event){
        TelemetryEvent event1=new TelemetryEvent();
        event1.setSensorId(event.sensorId());
        event1.setType(event.sensorType());
        event1.setLocation(event.location());
        event1.setUnit(event.unit());
        event1.setTimestamp(event.timestamp());
        event1.setValue(event.value());
        TelemetryEvent eventDto= telemetryService.saveTelemetry(event1);
        ResponseTelemetryEventDto response=new ResponseTelemetryEventDto(eventDto.getId(),
                eventDto.getSensorId(),
                eventDto.getType(),
                eventDto.getLocation(),
                eventDto.getTimestamp(),
                eventDto.getValue(),
                eventDto.getUnit());
        return response;
    }
    @GetMapping("/test")
    public String test() {
        return "Registry controller is working";
    }
}
