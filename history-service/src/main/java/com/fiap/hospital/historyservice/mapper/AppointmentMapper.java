package com.fiap.hospital.historyservice.mapper;

import com.fiap.hospital.historyservice.dto.AppointmentResponse;
import com.fiap.hospital.historyservice.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    @Mapping(target = "status", expression = "java(appointment.getStatus().name())")
    AppointmentResponse map(Appointment appointment);
}