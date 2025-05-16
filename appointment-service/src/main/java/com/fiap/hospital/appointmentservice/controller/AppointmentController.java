package com.fiap.hospital.appointmentservice.controller;

import com.fiap.hospital.appointmentservice.dto.AppointmentRequest;
import com.fiap.hospital.appointmentservice.dto.AppointmentResponse;
import com.fiap.hospital.appointmentservice.usecase.CreateAppointmentUseCase;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<AppointmentResponse> create(@RequestBody @Valid AppointmentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createAppointmentUseCase.execute(request));
    }
}
