package com.cm.bbEquityapi.exception;

import com.cm.bbEquityapi.dto.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse> handleException(Exception ex) {
        GenericResponse response=new GenericResponse(ex.getCause().getCause().getLocalizedMessage(),null);
       return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
