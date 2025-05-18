package com.fiap.hospital.appointmentservice.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenValidationResponse {
    private String username;
    private String role;
    private boolean valid;
}
