package com.fiap.hospital.appointmentservice.usecase.strategy;

import com.fiap.hospital.appointmentservice.dto.AppointmentRequest;

public interface CreateAppointmentStrategy {
    void execute(AppointmentRequest request);
}
