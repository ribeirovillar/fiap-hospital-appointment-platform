package com.fiap.hospital.historyservice.usecase;

import com.fiap.hospital.historyservice.configuration.AuthenticatedUser;
import com.fiap.hospital.historyservice.entity.Appointment;
import com.fiap.hospital.historyservice.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class FindAppointmentsByPatientUseCase {

    private static final String PATIENT = "PATIENT";
    AppointmentRepository repository;

    public List<Appointment> execute(Long idPatient) {
        AuthenticatedUser user = getCurrentUser();

        if (PATIENT.equalsIgnoreCase(user.getRole()) && !user.getId().equals(idPatient)) {
            throw new SecurityException("Access denied: patients can only view their own appointments");
        }

        return repository.findByIdPatient(idPatient);
    }

    private AuthenticatedUser getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (AuthenticatedUser) auth.getPrincipal();
    }
}
