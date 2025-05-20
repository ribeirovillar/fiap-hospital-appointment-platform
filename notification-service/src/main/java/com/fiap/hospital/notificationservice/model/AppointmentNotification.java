package com.fiap.hospital.notificationservice.model;

import com.fiap.hospital.notificationservice.enums.AppointmentStatus;
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
