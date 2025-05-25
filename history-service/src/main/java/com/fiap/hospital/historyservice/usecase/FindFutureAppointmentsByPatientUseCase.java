package com.fiap.hospital.historyservice.usecase;

import com.fiap.hospital.historyservice.entity.Appointment;
import com.fiap.hospital.historyservice.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class FindFutureAppointmentsByPatientUseCase implements AppointmentUseCase {

    AppointmentRepository repository;

    public List<Appointment> execute(Long idPatient) {
        accessValidation(idPatient);
        return repository.findAllByIdPatientAndAppointmentDateTimeAfter(idPatient, LocalDateTime.now());
    }
}
