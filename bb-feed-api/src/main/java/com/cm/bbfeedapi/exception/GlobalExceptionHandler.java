package com.cm.bbfeedapi.exception;


import com.cm.bbfeedapi.dto.GenericResponse;
import jakarta.servlet.http.HttpServletRequest;
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
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<GenericResponse> handleRuntimeException(RuntimeException ex, HttpServletRequest request) {
      GenericResponse errorResponse = new GenericResponse("Comment not found",  request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
