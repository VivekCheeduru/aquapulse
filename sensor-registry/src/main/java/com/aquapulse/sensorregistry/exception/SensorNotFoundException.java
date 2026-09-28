package com.aquapulse.sensorregistry.exception;

import lombok.Getter;

@Getter
public class SensorNotFoundException extends RuntimeException{
    private String message;
    public SensorNotFoundException(String message){
        this.message=message;
    }

}
