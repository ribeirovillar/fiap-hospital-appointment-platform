package com.fiap.hospital.appointmentservice.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfiguration {

    private final RabbitMQProperties rabbitMQProperties;

    @Bean
    public Queue appointmentQueue() {
        return new Queue(rabbitMQProperties.getQueue(), true);
    }

    @Bean
    public DirectExchange appointmentExchange() {
        return new DirectExchange(rabbitMQProperties.getExchange(), true, false);
    }

    @Bean
    public Binding binding(Queue appointmentQueue, DirectExchange appointmentExchange) {
        return BindingBuilder.bind(appointmentQueue)
                .to(appointmentExchange)
                .with(rabbitMQProperties.getRoutingkey());
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jackson2JsonMessageConverter());
        return template;
    }

}
