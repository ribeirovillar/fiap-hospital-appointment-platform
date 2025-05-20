package com.fiap.hospital.appointmentservice.mapper;

import com.fiap.hospital.appointmentservice.dto.AppointmentNotification;
import com.fiap.hospital.appointmentservice.dto.AppointmentRequest;
import com.fiap.hospital.appointmentservice.dto.AppointmentResponse;
import com.fiap.hospital.appointmentservice.entity.Appointment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    Appointment map(AppointmentRequest appointmentRequest);

    AppointmentResponse map(Appointment appointment);

    AppointmentNotification mapNotification(Appointment appointment);
}
