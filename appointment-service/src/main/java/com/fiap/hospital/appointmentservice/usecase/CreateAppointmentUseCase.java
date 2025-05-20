package com.fiap.hospital.appointmentservice.usecase;

import com.fiap.hospital.appointmentservice.entity.Appointment;
import com.fiap.hospital.appointmentservice.messaging.AppointmentPublisher;
import com.fiap.hospital.appointmentservice.repository.AppointmentRepository;
import com.fiap.hospital.appointmentservice.usecase.strategy.CreateAppointmentStrategy;
import com.fiap.hospital.appointmentservice.usecase.strategy.RetrieveAppointmentStrategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CreateAppointmentUseCase {

    AppointmentRepository appointmentRepository;
    List<CreateAppointmentStrategy> createAppointmentStrategies;
    List<RetrieveAppointmentStrategy> retrieveAppointmentStrategies;
    AppointmentPublisher appointmentPublisher;


    public Appointment execute(Appointment appointment) {

        createAppointmentStrategies.forEach(strategy -> strategy.execute(appointment));

        Appointment saved = appointmentRepository.save(appointment);

        retrieveAppointmentStrategies.forEach(strategy -> strategy.execute(saved));

        appointmentPublisher.publish(saved);

        return saved;

    }
}
