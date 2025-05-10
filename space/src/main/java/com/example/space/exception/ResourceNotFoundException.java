package com.example.space.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    private final int code;
    private final String message;

    public ResourceNotFoundException(String message) {
        this(HttpStatus.NOT_FOUND.value(), message);
    }

    public ResourceNotFoundException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public static ResourceNotFoundException of(String message) {
        return new ResourceNotFoundException(message);
    }

}
