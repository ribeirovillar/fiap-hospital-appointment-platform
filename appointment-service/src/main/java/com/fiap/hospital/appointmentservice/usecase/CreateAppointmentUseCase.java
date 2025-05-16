package com.fiap.hospital.appointmentservice.usecase;

import com.fiap.hospital.appointmentservice.dto.AppointmentRequest;
import com.fiap.hospital.appointmentservice.dto.AppointmentResponse;
import com.fiap.hospital.appointmentservice.entity.Appointment;
import com.fiap.hospital.appointmentservice.repository.AppointmentRepository;
import com.fiap.hospital.appointmentservice.usecase.strategy.CreateAppointmentStrategy;
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
    List<CreateAppointmentStrategy> strategies;


    public AppointmentResponse execute(AppointmentRequest request) {
        strategies.forEach(strategy -> strategy.execute(request));

        Appointment appointment = Appointment.builder()
                .idDoctor(request.getIdDoctor())
                .idPatient(request.getIdPatient())
                .idNurse(request.getIdNurse())
                .dateTime(request.getDateTime())
                .description(request.getDescription())
                .status(request.getStatus())
                .build();

        Appointment saved = appointmentRepository.save(appointment);

        return AppointmentResponse
                .builder()
                .id(saved.getId())
                .idPatient(saved.getIdPatient())
                //.patientName(saved.getPatientName())
                .idDoctor(saved.getIdDoctor())
                //.doctorName(saved.getDoctorName())
                .idNurse(saved.getIdNurse())
                //.nurseName(saved.getNurseName())
                .dateTime(saved.getDateTime())
                .description(saved.getDescription())
                .status(saved.getStatus())
                .build();
    }
}
