package com.fiap.hospital.historyservice.usecase;

import com.fiap.hospital.historyservice.configuration.AuthenticatedUser;
import com.fiap.hospital.historyservice.entity.Appointment;
import com.fiap.hospital.historyservice.exception.AppointmentPermissionException;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public interface AppointmentUseCase {

    String PATIENT = "PATIENT";

    List<Appointment> execute(Long idPatient);

    default void accessValidation(Long idPatient) {
        AuthenticatedUser user = getCurrentUser();
        if (PATIENT.equalsIgnoreCase(user.getRole()) && !user.getId().equals(idPatient)) {
            throw new AppointmentPermissionException("Access denied: patients can only view their own appointments");
        }
    }

    default AuthenticatedUser getCurrentUser() {
        return (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
