package com.fiap.hospital.appointmentservice.usecase;


import com.fiap.hospital.appointmentservice.configuration.AuthenticatedUser;
import com.fiap.hospital.appointmentservice.entity.Appointment;
import com.fiap.hospital.appointmentservice.exception.AppointmentNotFoundException;
import com.fiap.hospital.appointmentservice.repository.AppointmentRepository;
import com.fiap.hospital.appointmentservice.usecase.strategy.RetrieveAppointmentStrategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RetrieveAppointmentByIdUseCase {

    AppointmentRepository appointmentRepository;
    List<RetrieveAppointmentStrategy> retrieveAppointmentStrategies;

    public Appointment execute(Long id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AuthenticatedUser user = (AuthenticatedUser) auth.getPrincipal();

        Appointment appointment;

        if ("PATIENT".equalsIgnoreCase(user.getRole())) {
            Long idPatient = user.getId();
            appointment = appointmentRepository.findByIdAndIdPatient(id, idPatient).orElseThrow(() -> new AppointmentNotFoundException("Appointment not found or you do not have permission to access it"));
        } else {
            appointment = appointmentRepository.findById(id).orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with id: " + id));
        }

        retrieveAppointmentStrategies.forEach(strategy -> strategy.execute(appointment));

        return appointment;
    }

}
