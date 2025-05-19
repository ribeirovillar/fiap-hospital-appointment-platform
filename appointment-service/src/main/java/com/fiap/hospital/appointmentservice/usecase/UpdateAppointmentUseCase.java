package com.fiap.hospital.appointmentservice.usecase;

import com.fiap.hospital.appointmentservice.entity.Appointment;
import com.fiap.hospital.appointmentservice.repository.AppointmentRepository;
import com.fiap.hospital.appointmentservice.usecase.strategy.UpdateAppointmentStrategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UpdateAppointmentUseCase {
    AppointmentRepository appointmentRepository;
    List<UpdateAppointmentStrategy> updateAppointmentStrategies;

    public Appointment execute(Appointment appointment) {
        updateAppointmentStrategies.forEach(strategy -> strategy.execute(appointment));
        return appointmentRepository.save(appointment);
    }
}
