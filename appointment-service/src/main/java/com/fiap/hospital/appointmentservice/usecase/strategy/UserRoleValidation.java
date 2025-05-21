package com.fiap.hospital.appointmentservice.usecase.strategy;

import com.fiap.hospital.appointmentservice.entity.Appointment;
import com.fiap.hospital.appointmentservice.enums.Role;
import com.fiap.hospital.appointmentservice.service.UserRoleValidationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserRoleValidation implements CreateAppointmentStrategy, UpdateAppointmentStrategy {

    UserRoleValidationService userRoleValidationService;

    @Override
    public void execute(Appointment appointment) {
        userRoleValidationService.validateUserIdByRole(appointment.getIdDoctor(), Role.DOCTOR.name());
        userRoleValidationService.validateUserIdByRole(appointment.getIdPatient(), Role.PATIENT.name());
        if (appointment.getIdNurse() != null) {
            userRoleValidationService.validateUserIdByRole(appointment.getIdNurse(), Role.NURSE.name());
        }
    }
}
