package com.rustambek.clinic.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiException extends RuntimeException {
    private final String message;
    public ApiException(String message) {
        this.message = message;
    }

}