package com.fiap.hospital.historyservice.controller;

import com.fiap.hospital.historyservice.dto.AppointmentResponse;
import com.fiap.hospital.historyservice.mapper.AppointmentMapper;
import com.fiap.hospital.historyservice.usecase.FindAppointmentsByPatientUseCase;
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

    @QueryMapping
    public List<AppointmentResponse> appointmentsByPatient(@Argument Long idPatient) {
        return findAppointmentsByPatientUseCase.execute(idPatient).stream().map(mapper::map).toList();
    }

}
