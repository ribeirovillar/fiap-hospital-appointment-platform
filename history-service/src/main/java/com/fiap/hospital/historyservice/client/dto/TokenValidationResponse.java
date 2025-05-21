package com.fiap.hospital.historyservice.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenValidationResponse {
    private Long id;
    private String username;
    private String role;
    private boolean valid;
}
