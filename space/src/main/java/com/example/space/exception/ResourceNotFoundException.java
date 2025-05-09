package com.example.space.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("未找到资源");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
