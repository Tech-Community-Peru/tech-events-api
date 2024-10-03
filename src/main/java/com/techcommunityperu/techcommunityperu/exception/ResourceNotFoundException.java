package com.techcommunityperu.techcommunityperu.exception;

public class ResourceNotFoundException extends RuntimeException {

    ResourceNotFoundException(){
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
