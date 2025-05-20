package com.fiap.hospital.notificationservice.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Value("${rabbitmq.queue}")
    private String queue;

    @Bean
    public Queue appointmentNotificationQueue() {
        return new Queue(queue, true);
    }
}
