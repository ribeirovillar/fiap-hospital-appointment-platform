package com.fiap.hospital.historyservice.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentResponse {
    Long id;
    String description;
    LocalDateTime appointmentDateTime;
    String status;
    Long idPatient;
    String patientName;
    Long idDoctor;
    String doctorName;
    Long idNurse;
    String nurseName;
}

