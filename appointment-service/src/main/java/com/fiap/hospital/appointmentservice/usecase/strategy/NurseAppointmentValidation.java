package com.fiap.hospital.appointmentservice.usecase.strategy;

import com.fiap.hospital.appointmentservice.entity.Appointment;
import com.fiap.hospital.appointmentservice.exception.NurseAlreadyHasAppointmentException;
import com.fiap.hospital.appointmentservice.repository.AppointmentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class NurseAppointmentValidation implements CreateAppointmentStrategy {

    AppointmentRepository appointmentRepository;

    @Override
    public void execute(Appointment appointment) {
        if (appointmentRepository.existsByIdNurseAndAppointmentDateTime(appointment.getIdNurse(), appointment.getAppointmentDateTime())) {
            throw new NurseAlreadyHasAppointmentException("Nurse already has an appointment at the given date/time.");
        }
    }
}
