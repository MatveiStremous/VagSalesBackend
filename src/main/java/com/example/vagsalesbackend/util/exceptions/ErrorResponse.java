package com.example.vagsalesbackend.util.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse {
    private String message;
    private long timestamp;

    public ErrorResponse(String message) {
        this.message = message;
        timestamp = System.currentTimeMillis();
    }
}
