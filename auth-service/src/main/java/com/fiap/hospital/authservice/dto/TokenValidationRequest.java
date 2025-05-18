package com.fiap.hospital.authservice.dto;

import lombok.Data;

@Data
public class TokenValidationRequest {
    private String token;
}
