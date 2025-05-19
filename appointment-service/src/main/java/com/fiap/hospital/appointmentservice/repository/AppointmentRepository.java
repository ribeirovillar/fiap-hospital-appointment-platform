package com.fiap.hospital.appointmentservice.repository;

import com.fiap.hospital.appointmentservice.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Optional<Appointment> findByIdDoctorAndAppointmentDateTime(Long idDoctor, LocalDateTime appointmentDateTime);

    Optional<Appointment> findByIdPatientAndAppointmentDateTime(Long idPatient, LocalDateTime appointmentDateTime);

    Optional<Appointment> findByIdNurseAndAppointmentDateTime(Long idNurse, LocalDateTime appointmentDateTime);

    List<Appointment> findAllByIdPatient(Long idPatient);

    Optional<Appointment> findByIdAndIdPatient(Long id, Long idPatient);
}
