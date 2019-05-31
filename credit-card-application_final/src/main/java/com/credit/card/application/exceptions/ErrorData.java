package com.credit.card.application.exceptions;

public class ErrorData {

    private String field;
    private String errorMessage;

    public String getField() {
        return field;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}