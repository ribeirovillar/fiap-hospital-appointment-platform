package com.fiap.hospital.appointmentservice.usecase.strategy;

import com.fiap.hospital.appointmentservice.entity.Appointment;

public interface CreateAppointmentStrategy {
    void execute(Appointment appointment);
}
