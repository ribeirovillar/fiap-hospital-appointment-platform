package com.fiap.hospital.appointmentservice.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "rabbitmq")
@Data
public class RabbitMQProperties {
    private String queue;
    private String exchange;
    private String routingkey;
}
