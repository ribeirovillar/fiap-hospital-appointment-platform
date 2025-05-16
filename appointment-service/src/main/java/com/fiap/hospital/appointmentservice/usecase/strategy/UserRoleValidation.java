package com.fiap.hospital.appointmentservice.usecase.strategy;

import com.fiap.hospital.appointmentservice.dto.AppointmentRequest;
import com.fiap.hospital.appointmentservice.service.UserValidationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserRoleValidation implements CreateAppointmentStrategy {

    UserValidationService userValidationService;

    @Override
    public void execute(AppointmentRequest request) {
        userValidationService.validateUserIdByRole(request.getIdDoctor(), "DOCTOR");
        userValidationService.validateUserIdByRole(request.getIdPatient(), "PATIENT");
        if (request.getIdNurse() != null) {
            userValidationService.validateUserIdByRole(request.getIdNurse(), "NURSE");
        }
    }
}
