package com.mandiriecash.etollapi.controllers;


import com.mandiriecash.etollapi.exceptions.staffs.TokenStaffNotFoundException;
import com.mandiriecash.etollapi.exceptions.staffs.TokenStaffNotProvidedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler({
            TokenStaffNotFoundException.class, TokenStaffNotProvidedException.class
    })
    public ResponseEntity<ErrorResponse> handleDefault(Exception exception){
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(exception.getMessage()),HttpStatus.UNAUTHORIZED);
    }

    class ErrorResponse {
        public final String status = "error";
        public final String message;
        public ErrorResponse (String message){
            this.message = message;
        }
    }
}
