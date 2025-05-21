package com.fiap.hospital.historyservice.repository;

import com.fiap.hospital.historyservice.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByIdPatient(Long idPatient);

}
