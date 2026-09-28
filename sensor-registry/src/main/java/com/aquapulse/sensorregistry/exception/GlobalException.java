package com.aquapulse.sensorregistry.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;

@RestControllerAdvice
public class GlobalException{

    @ExceptionHandler(SensorNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegal(SensorNotFoundException ex, HttpServletRequest request){
        ApiErrorResponse response=new ApiErrorResponse(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "SENSOR_NOT_FOUND",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(response);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationException(MethodArgumentNotValidException ex,HttpServletRequest request){
       String message= ex.getBindingResult().
        getFieldErrors().
               stream().
               map(error->error.getField()+":"+error.getDefaultMessage()).
               collect(Collectors.joining());
        ApiErrorResponse response=new ApiErrorResponse(
                Instant.now(),
                HttpStatusCode.valueOf(400).value(),
                "VALIDATION ERROR",
                message,
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(response);
    }

    @ExceptionHandler(SensorAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleAlreadyExist(SensorAlreadyExistsException ex,HttpServletRequest request){
        ApiErrorResponse response=new ApiErrorResponse(
                Instant.now(),
                HttpStatusCode.valueOf(409).value(),
                "ALREADY SENSOR ID EXISTS",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatusCode.valueOf(409)).body(response);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleMessageNotReadable(HttpMessageNotReadableException ex,HttpServletRequest request){
        ApiErrorResponse response=new ApiErrorResponse(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "MALFORMED REQUEST",
                "Invalid Request Body",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
