package com.fiap.hospital.appointmentservice.usecase;


import com.fiap.hospital.appointmentservice.client.AuthServiceClient;
import com.fiap.hospital.appointmentservice.entity.Appointment;
import com.fiap.hospital.appointmentservice.exception.AppointmentNotFoundException;
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
public class RetrieveAppointmentByIdUseCase {

    AppointmentRepository appointmentRepository;
    List<RetrieveAppointmentStrategy> retrieveAppointmentStrategies;
    AuthServiceClient authServiceClient;
    RoleVerificationService roleVerificationService;

    public Appointment execute(Long id) {
        boolean isPatient = roleVerificationService.isRole("PATIENT");

        Appointment appointment = isPatient
                ? appointmentRepository.findByIdAndIdPatient(id, authServiceClient.getCurrentUserInfo().getId()).orElseThrow(() -> new AppointmentNotFoundException("Appointment not found or you do not have permission to access it"))
                : appointmentRepository.findById(id).orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with id: " + id));

        retrieveAppointmentStrategies.forEach(strategy -> strategy.execute(appointment));

        return appointment;
    }

}
