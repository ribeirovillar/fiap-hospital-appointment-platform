package com.fiap.hospital.appointmentservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.hospital.appointmentservice.enums.AppointmentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentRequest {

    @NotNull(message = "Patient ID cannot be null")
    @JsonProperty(required = true)
    Long idPatient;

    @NotNull(message = "Doctor ID cannot be null")
    @JsonProperty(required = true)
    Long idDoctor;

    Long idNurse;

    @NotNull(message = "Date and time cannot be null")
    @JsonProperty(required = true)
    LocalDateTime appointmentDateTime;

    @NotNull(message = "Description cannot be null")
    @JsonProperty(required = true)
    String description;

    @NotNull(message = "Status cannot be null")
    @JsonProperty(required = true)
    AppointmentStatus status;
}
