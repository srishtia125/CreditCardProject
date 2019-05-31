package com.credit.card.application.exceptions;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ErrorResponseHelper {

    public static ErrorResponse getErrorResponse(ConstraintViolationException ex) {
        ErrorResponse response = new ErrorResponse();
        List<ErrorData> errorDataList=null;
        response.setGlobalErrorMessage("Request field(s) validation error");
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        if(null!=constraintViolations && !constraintViolations.isEmpty()){
            errorDataList= new ArrayList<>(constraintViolations.size());
            for(ConstraintViolation constraintViolation : constraintViolations){
                ErrorData errorData = new ErrorData();
                errorData.setField(constraintViolation.getPropertyPath().toString());
                errorData.setErrorMessage(constraintViolation.getMessage());
                errorDataList.add(errorData);
            }
        response.setErrorDetails(errorDataList);
        }
        return response;
    }

    public static ErrorResponse getErrorResponse(Exception ex) {
        if(ex instanceof ConstraintViolationException){
            return getErrorResponse((ConstraintViolationException)ex);
        }
        ErrorResponse response = new ErrorResponse();
        response.setGlobalErrorMessage(ex.getMessage());
        return response;
    }
}
