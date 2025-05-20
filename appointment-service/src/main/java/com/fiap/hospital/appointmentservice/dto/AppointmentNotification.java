package com.fiap.hospital.appointmentservice.dto;

import com.fiap.hospital.appointmentservice.enums.AppointmentStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentNotification {
    Long id;
    Long idPatient;
    String patientName;
    Long idDoctor;
    String doctorName;
    Long idNurse;
    String nurseName;
    LocalDateTime appointmentDateTime;
    String description;
    AppointmentStatus status;
}
