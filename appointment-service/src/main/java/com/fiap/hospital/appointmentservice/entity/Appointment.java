package com.fiap.hospital.appointmentservice.entity;

import com.fiap.hospital.appointmentservice.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
