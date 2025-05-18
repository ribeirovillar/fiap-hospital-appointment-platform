package com.fiap.hospital.appointmentservice.usecase.strategy;

import com.fiap.hospital.appointmentservice.entity.Appointment;
import com.fiap.hospital.appointmentservice.exception.DoctorAlreadyHasAppointmentException;
import com.fiap.hospital.appointmentservice.repository.AppointmentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class DoctorAppointmentValidation implements CreateAppointmentStrategy {

    AppointmentRepository appointmentRepository;

    @Override
    public void execute(Appointment appointment) {
        if (appointmentRepository.existsByIdDoctorAndAppointmentDateTime(appointment.getIdDoctor(), appointment.getAppointmentDateTime())) {
            throw new DoctorAlreadyHasAppointmentException("Doctor already has an appointment at the given date/time.");
        }
    }
}
