package com.ecommerce.user_service.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResponseDto {
    private String apiPath;
    private String errorMessage;
    private HttpStatus errorCode;
    private LocalDateTime errorTime;


    public ErrorResponseDto(String description, HttpStatus httpStatus, String message, LocalDateTime now) {
        this.apiPath=description;
        this.errorCode=httpStatus;
        this.errorTime=now;
        this.errorMessage=message;
    }

    public LocalDateTime getErrorTime() {
        return errorTime;
    }

    public void setErrorTime(LocalDateTime errorTime) {
        this.errorTime = errorTime;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(HttpStatus errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }
}
