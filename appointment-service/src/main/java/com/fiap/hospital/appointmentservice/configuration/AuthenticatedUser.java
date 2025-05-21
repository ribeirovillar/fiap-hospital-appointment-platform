package com.fiap.hospital.appointmentservice.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticatedUser {
    private Long id;
    private String username;
    private String role;
}

