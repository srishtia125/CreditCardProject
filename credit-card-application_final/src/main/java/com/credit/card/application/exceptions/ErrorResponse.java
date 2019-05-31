package com.credit.card.application.exceptions;

import java.util.List;

public class ErrorResponse {

    private String globalErrorMessage;
    private List<ErrorData> errorDetails;

    public String getGlobalErrorMessage() {
        return globalErrorMessage;
    }

    public void setGlobalErrorMessage(String globalErrorMessage) {
        this.globalErrorMessage = globalErrorMessage;
    }

    public List<ErrorData> getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(List<ErrorData> errorDetails) {
        this.errorDetails = errorDetails;
    }
}
