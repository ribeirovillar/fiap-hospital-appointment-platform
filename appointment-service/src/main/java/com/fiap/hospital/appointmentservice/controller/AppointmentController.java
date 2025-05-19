package com.fiap.hospital.appointmentservice.controller;

import com.fiap.hospital.appointmentservice.dto.AppointmentRequest;
import com.fiap.hospital.appointmentservice.dto.AppointmentResponse;
import com.fiap.hospital.appointmentservice.entity.Appointment;
import com.fiap.hospital.appointmentservice.mapper.AppointmentMapper;
import com.fiap.hospital.appointmentservice.usecase.CreateAppointmentUseCase;
import com.fiap.hospital.appointmentservice.usecase.RetrieveAllAppointmentUseCase;
import com.fiap.hospital.appointmentservice.usecase.RetrieveAppointmentByIdUseCase;
import com.fiap.hospital.appointmentservice.usecase.UpdateAppointmentUseCase;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AppointmentController {

    CreateAppointmentUseCase createAppointmentUseCase;
    UpdateAppointmentUseCase updateAppointmentUseCase;
    RetrieveAllAppointmentUseCase retrieveAllAppointmentUseCase;
    RetrieveAppointmentByIdUseCase retrieveAppointmentByIdUseCase;
    AppointmentMapper appointmentMapper;

    @PostMapping
    public ResponseEntity<AppointmentResponse> create(@RequestBody @Valid AppointmentRequest request) {
        Appointment appointment = appointmentMapper.map(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentMapper.map(createAppointmentUseCase.execute(appointment)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponse> update(@PathVariable("id") Long id, @RequestBody @Valid AppointmentRequest request) {
        Appointment appointment = appointmentMapper.map(request);
        appointment.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(appointmentMapper.map(updateAppointmentUseCase.execute(appointment)));
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(retrieveAllAppointmentUseCase
                .execute()
                .stream().map(appointmentMapper::map)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponse> getById(@PathVariable("id") Long id) {
        Appointment appointment = retrieveAppointmentByIdUseCase.execute(id);
        return ResponseEntity.status(HttpStatus.OK).body(appointmentMapper.map(appointment));
    }
}
