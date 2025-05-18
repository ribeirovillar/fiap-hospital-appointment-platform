package com.fiap.hospital.appointmentservice.repository;

import com.fiap.hospital.appointmentservice.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByIdPatientAndAppointmentDateTime(Long patientId, LocalDateTime appointmentDateTime);

    boolean existsByIdDoctorAndAppointmentDateTime(Long doctorId, LocalDateTime appointmentDateTime);

    boolean existsByIdNurseAndAppointmentDateTime(Long nurseId, LocalDateTime appointmentDateTime);


}
