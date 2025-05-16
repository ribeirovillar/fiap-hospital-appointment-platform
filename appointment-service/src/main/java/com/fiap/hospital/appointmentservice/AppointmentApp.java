package com.fiap.hospital.appointmentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.fiap.hospital.appointmentservice.client")
@SpringBootApplication
public class AppointmentApp {

    public static void main(String[] args) {
        SpringApplication.run(AppointmentApp.class, args);
    }

}
