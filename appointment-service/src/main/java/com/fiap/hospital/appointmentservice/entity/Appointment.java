package com.fiap.hospital.appointmentservice.entity;

import com.fiap.hospital.appointmentservice.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idPatient;

    private Long idDoctor;

    private Long idNurse;

    private LocalDateTime dateTime;

    private String description;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
}
