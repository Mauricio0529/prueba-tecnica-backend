package com.gcatechnologies.exceptions;

import lombok.Getter;

/**
 * Mensaje de error response
 */
@Getter
public class ErrorResponse {

    private String errorMessage;

    public ErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}