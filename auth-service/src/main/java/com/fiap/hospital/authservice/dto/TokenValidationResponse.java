package com.fiap.hospital.authservice.dto;

import com.fiap.hospital.authservice.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenValidationResponse {
    private String username;
    private Role role;
    private boolean valid;
}
