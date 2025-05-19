package com.fiap.hospital.appointmentservice.usecase.strategy;

import com.fiap.hospital.appointmentservice.entity.Appointment;
import com.fiap.hospital.appointmentservice.exception.AppointmentNotFoundException;
import com.fiap.hospital.appointmentservice.repository.AppointmentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AppointmentExistsValidation implements UpdateAppointmentStrategy {

    AppointmentRepository appointmentRepository;

    @Override
    public void execute(Appointment appointment) {
        appointmentRepository.findById(appointment.getId())
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with id: " + appointment.getId()));
    }
}
