package com.credit.card.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class CloudViewExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        return getErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleMethodArgumentViolationException(ConstraintViolationException ex) {
        return getErrorResponse(ex,HttpStatus.BAD_REQUEST);
    }


    private ResponseEntity<ErrorResponse> getErrorResponse(Exception ex, HttpStatus status){
        try{
            return new ResponseEntity<>(ErrorResponseHelper.getErrorResponse(ex),status);
        }catch(Exception exception){
            throw exception;
        }
    }

}

