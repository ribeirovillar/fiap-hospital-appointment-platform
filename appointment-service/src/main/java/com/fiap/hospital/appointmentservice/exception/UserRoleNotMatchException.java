package com.fiap.hospital.appointmentservice.exception;

public class UserRoleNotMatchException extends RuntimeException {
    public UserRoleNotMatchException(String message) {
        super(message);
    }
}
