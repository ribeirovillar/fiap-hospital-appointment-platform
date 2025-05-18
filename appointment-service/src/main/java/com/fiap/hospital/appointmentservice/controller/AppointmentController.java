package com.fiap.hospital.appointmentservice.controller;

import com.fiap.hospital.appointmentservice.dto.AppointmentRequest;
import com.fiap.hospital.appointmentservice.dto.AppointmentResponse;
import com.fiap.hospital.appointmentservice.entity.Appointment;
import com.fiap.hospital.appointmentservice.mapper.AppointmentMapper;
import com.fiap.hospital.appointmentservice.usecase.CreateAppointmentUseCase;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AppointmentController {

    CreateAppointmentUseCase createAppointmentUseCase;
    AppointmentMapper appointmentMapper;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_NURSE')")
    public ResponseEntity<AppointmentResponse> create(@RequestBody @Valid AppointmentRequest request) {
        Appointment appointment = appointmentMapper.map(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentMapper.map(createAppointmentUseCase.execute(appointment)));
    }
}
