package com.erabia.studentapi.dto;

public class ErrorResponseDTO {
    private String errorType;
    private String errorMessage;

    public ErrorResponseDTO() {}

    public ErrorResponseDTO(String errorType, String errorMessage) {
        this.errorType = errorType;
        this.errorMessage = errorMessage;
    }
    public String getErrorType() {
        return errorType;
    }
    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "ErrorListWsDTO{" +
                "errorType='" + errorType + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
