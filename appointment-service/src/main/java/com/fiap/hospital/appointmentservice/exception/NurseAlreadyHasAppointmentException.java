package com.fiap.hospital.appointmentservice.exception;

public class NurseAlreadyHasAppointmentException extends RuntimeException {
    public NurseAlreadyHasAppointmentException(String message) {
        super(message);
    }
}
