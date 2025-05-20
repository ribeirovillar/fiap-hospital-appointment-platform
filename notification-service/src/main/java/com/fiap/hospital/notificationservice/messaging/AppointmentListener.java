package com.fiap.hospital.notificationservice.messaging;

import com.fiap.hospital.notificationservice.model.AppointmentNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AppointmentListener {

@RabbitListener(queues = "${rabbitmq.queue}")
    public void receive(AppointmentNotification notification) {
        log.info("📢 Appointment notification received: {}", notification);
    }
}
