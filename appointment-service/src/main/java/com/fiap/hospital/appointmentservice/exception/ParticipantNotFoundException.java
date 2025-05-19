package com.fiap.hospital.appointmentservice.exception;

public class ParticipantNotFoundException extends RuntimeException {
  public ParticipantNotFoundException(String message) {
    super(message);
  }
}
