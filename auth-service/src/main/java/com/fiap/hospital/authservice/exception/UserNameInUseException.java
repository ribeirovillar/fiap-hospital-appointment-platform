package com.fiap.hospital.authservice.exception;

public class UserNameInUseException extends RuntimeException {
    public UserNameInUseException(String message) {
        super(message);
    }
}
