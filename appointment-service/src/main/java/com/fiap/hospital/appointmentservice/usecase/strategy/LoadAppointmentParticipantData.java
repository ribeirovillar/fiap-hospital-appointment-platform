package com.fiap.hospital.appointmentservice.usecase.strategy;

import com.fiap.hospital.appointmentservice.client.AuthServiceClient;
import com.fiap.hospital.appointmentservice.entity.Appointment;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class LoadAppointmentParticipantData implements RetrieveAppointmentStrategy {

    AuthServiceClient authServiceClient;

    @Override
    public void execute(Appointment appointment) {
        appointment.setPatientName(authServiceClient.getUserInfoById(appointment.getIdPatient()).getName());
        appointment.setDoctorName(authServiceClient.getUserInfoById(appointment.getIdDoctor()).getName());
        if (appointment.getIdNurse() != null) {
            appointment.setNurseName(authServiceClient.getUserInfoById(appointment.getIdNurse()).getName());
        }
    }
}
