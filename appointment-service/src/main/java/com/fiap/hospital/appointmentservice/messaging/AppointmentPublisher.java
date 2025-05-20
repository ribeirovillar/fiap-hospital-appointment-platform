package com.fiap.hospital.appointmentservice.messaging;

import com.fiap.hospital.appointmentservice.configuration.RabbitMQProperties;
import com.fiap.hospital.appointmentservice.entity.Appointment;
import com.fiap.hospital.appointmentservice.mapper.AppointmentMapper;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class AppointmentPublisher {

    RabbitTemplate rabbitTemplate;
    RabbitMQProperties rabbitMQProperties;
    AppointmentMapper appointmentMapper;

    public void publish(Appointment appointment) {
        rabbitTemplate.convertAndSend(rabbitMQProperties.getExchange(), rabbitMQProperties.getRoutingkey(), appointmentMapper.mapNotification(appointment));
    }
}
