package com.fiap.hospital.appointmentservice.usecase;

import com.fiap.hospital.appointmentservice.configuration.AuthenticatedUser;
import com.fiap.hospital.appointmentservice.entity.Appointment;
import com.fiap.hospital.appointmentservice.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.fiap.hospital.appointmentservice.enums.Role.PATIENT;

@Component
@RequiredArgsConstructor
public class RetrieveAllAppointmentUseCase {

    private final AppointmentRepository appointmentRepository;

    public List<Appointment> execute() {
        AuthenticatedUser user = getCurrentUser();

        return PATIENT.name().equalsIgnoreCase(user.getRole())
                ? appointmentRepository.findAllByIdPatient(user.getId())
                : appointmentRepository.findAll();
    }

    private AuthenticatedUser getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (AuthenticatedUser) auth.getPrincipal();
    }
}