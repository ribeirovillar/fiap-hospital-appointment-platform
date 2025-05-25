package com.fiap.hospital.historyservice.usecase;

import com.fiap.hospital.historyservice.configuration.AuthenticatedUser;
import com.fiap.hospital.historyservice.entity.Appointment;
import com.fiap.hospital.historyservice.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class FindFutureAppointments {

    private final AppointmentRepository appointmentRepository;

    public List<Appointment> execute() {
        AuthenticatedUser user = getCurrentUser();

        return "PATIENT".equalsIgnoreCase(user.getRole())
                ? appointmentRepository.findAllByIdPatientAndAppointmentDateTimeAfter(user.getId(), LocalDateTime.now())
                : appointmentRepository.findAllByAppointmentDateTimeAfter(LocalDateTime.now());
    }

    private AuthenticatedUser getCurrentUser() {
        return (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
