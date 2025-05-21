package com.fiap.hospital.historyservice.entity;

import com.fiap.hospital.historyservice.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long idPatient;
    String patientName;
    Long idDoctor;
    String doctorName;
    Long idNurse;
    String nurseName;
    LocalDateTime appointmentDateTime;
    String description;
    @Enumerated(EnumType.STRING)
    AppointmentStatus status;
}
