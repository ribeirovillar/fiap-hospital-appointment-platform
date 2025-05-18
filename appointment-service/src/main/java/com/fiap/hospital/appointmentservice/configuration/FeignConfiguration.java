package com.fiap.hospital.appointmentservice.configuration;

import feign.Contract;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {
    @Bean
    public Contract feignContract() {
        return new SpringMvcContract();
    }
}