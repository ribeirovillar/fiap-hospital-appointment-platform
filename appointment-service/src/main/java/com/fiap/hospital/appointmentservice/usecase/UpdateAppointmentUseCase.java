package com.fiap.hospital.appointmentservice.usecase;

import com.fiap.hospital.appointmentservice.entity.Appointment;
import com.fiap.hospital.appointmentservice.messaging.AppointmentPublisher;
import com.fiap.hospital.appointmentservice.repository.AppointmentRepository;
import com.fiap.hospital.appointmentservice.usecase.strategy.UpdateAppointmentStrategy;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class UpdateAppointmentUseCase {
    AppointmentRepository appointmentRepository;
    List<UpdateAppointmentStrategy> updateAppointmentStrategies;
    AppointmentPublisher appointmentPublisher;

    @Transactional
    public Appointment execute(Appointment appointment) {
        updateAppointmentStrategies.forEach(strategy -> strategy.execute(appointment));
        Appointment appointmentUpdated = update(appointment);
        notifyAppointmentUpdate(appointmentUpdated);
        return appointmentUpdated;
    }

    private Appointment update(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    private void notifyAppointmentUpdate(Appointment appointment) {
        try {
            appointmentPublisher.publish(appointment);
        } catch (Exception e) {
            log.error("Failed to publish appointment: {}", e.getMessage());
        }
    }
}
