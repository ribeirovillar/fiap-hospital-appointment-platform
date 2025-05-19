package com.fiap.hospital.appointmentservice.usecase;

import com.fiap.hospital.appointmentservice.client.AuthServiceClient;
import com.fiap.hospital.appointmentservice.entity.Appointment;
import com.fiap.hospital.appointmentservice.repository.AppointmentRepository;
import com.fiap.hospital.appointmentservice.service.RoleVerificationService;
import com.fiap.hospital.appointmentservice.usecase.strategy.RetrieveAppointmentStrategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RetrieveAllAppointmentUseCase {

    AppointmentRepository appointmentRepository;
    List<RetrieveAppointmentStrategy> retrieveAppointmentStrategies;
    AuthServiceClient authServiceClient;
    RoleVerificationService roleVerificationService;

    public List<Appointment> execute() {
        boolean isPatient = roleVerificationService.isRole("PATIENT");

        List<Appointment> appointments = isPatient
                ? appointmentRepository.findAllByIdPatient(authServiceClient.getCurrentUserInfo().getId())
                : appointmentRepository.findAll();

        return appointments.stream()
                .peek(appointment -> retrieveAppointmentStrategies.forEach(strategy -> strategy.execute(appointment)))
                .toList();
    }
}