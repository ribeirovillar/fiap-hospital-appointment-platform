package com.fiap.hospital.appointmentservice.dto;

import com.fiap.hospital.appointmentservice.enums.AppointmentStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentResponse {
    Long id;
    Long idPatient;
    String patientName;
    Long idDoctor;
    String doctorName;
    Long idNurse;
    String nurseName;
    LocalDateTime dateTime;
    String description;
    AppointmentStatus status;
}
