package com.fiap.hospital.appointmentservice.usecase;


import com.fiap.hospital.appointmentservice.configuration.AuthenticatedUser;
import com.fiap.hospital.appointmentservice.entity.Appointment;
import com.fiap.hospital.appointmentservice.exception.AppointmentNotFoundException;
import com.fiap.hospital.appointmentservice.exception.AppointmentPermissionException;
import com.fiap.hospital.appointmentservice.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import static com.fiap.hospital.appointmentservice.enums.Role.PATIENT;

@Component
@RequiredArgsConstructor
public class RetrieveAppointmentByIdUseCase {

    private final AppointmentRepository appointmentRepository;

    public Appointment execute(Long id) {
        AuthenticatedUser user = getCurrentUser();

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with id: " + id));

        if (PATIENT.name().equalsIgnoreCase(user.getRole()) && !appointment.getIdPatient().equals(user.getId())) {
            throw new AppointmentPermissionException("Access denied: patients can only view their own appointments");
        }

        return appointment;
    }

    private AuthenticatedUser getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (AuthenticatedUser) auth.getPrincipal();
    }
}
