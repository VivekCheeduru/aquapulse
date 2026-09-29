package com.aquapulse.sensorsimulator.exceptions;

public class FactoryFailureException extends RuntimeException{
    private String message;
    public FactoryFailureException(String message){
        this.message=message;
    }
}
