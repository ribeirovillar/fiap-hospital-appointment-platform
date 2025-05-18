package com.fiap.hospital.appointmentservice.exception;

public class DoctorAlreadyHasAppointmentException extends RuntimeException {
    public DoctorAlreadyHasAppointmentException(String message) {
        super(message);
    }
}
