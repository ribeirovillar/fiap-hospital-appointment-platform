package com.fiap.hospital.historyservice.controller;

import com.fiap.hospital.historyservice.dto.AppointmentResponse;
import com.fiap.hospital.historyservice.mapper.AppointmentMapper;
import com.fiap.hospital.historyservice.usecase.FindAppointmentsByPatientUseCase;
import com.fiap.hospital.historyservice.usecase.FindFutureAppointments;
import com.fiap.hospital.historyservice.usecase.FindFutureAppointmentsByPatientUseCase;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AppointmentGraphQLController {

    AppointmentMapper mapper;
    FindAppointmentsByPatientUseCase findAppointmentsByPatientUseCase;
    FindFutureAppointmentsByPatientUseCase findFutureAppointmentsByPatientUseCase;
    FindFutureAppointments findFutureAppointmentsUseCase;

    @QueryMapping
    public List<AppointmentResponse> appointmentsByPatient(@Argument Long idPatient) {
        return findAppointmentsByPatientUseCase.execute(idPatient).stream().map(mapper::map).toList();
    }

    @QueryMapping
    public List<AppointmentResponse> futureAppointmentsByPatient(@Argument Long idPatient) {
        return findFutureAppointmentsByPatientUseCase.execute(idPatient).stream().map(mapper::map).toList();
    }

    @QueryMapping
    public List<AppointmentResponse> futureAppointments() {
        return findFutureAppointmentsUseCase.execute().stream().map(mapper::map).toList();
    }

}
