package com.fiap.hospital.appointmentservice.usecase;

import com.fiap.hospital.appointmentservice.configuration.AuthenticatedUser;
import com.fiap.hospital.appointmentservice.entity.Appointment;
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
public class RetrieveAllAppointmentUseCase {

    AppointmentRepository appointmentRepository;
    List<RetrieveAppointmentStrategy> retrieveAppointmentStrategies;

    public List<Appointment> execute() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AuthenticatedUser user = (AuthenticatedUser) auth.getPrincipal();

        List<Appointment> appointments;

        if ("PATIENT".equalsIgnoreCase(user.getRole())) {
            Long id = user.getId();
            appointments = appointmentRepository.findAllByIdPatient(id);
        } else {
            appointments = appointmentRepository.findAll();
        }

        return appointments.stream()
                .peek(appointment -> retrieveAppointmentStrategies.forEach(strategy -> strategy.execute(appointment)))
                .toList();
    }
}