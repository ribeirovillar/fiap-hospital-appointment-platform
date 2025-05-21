package com.fiap.hospital.historyservice.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticatedUser {
    private Long id;
    private String username;
    private String role;
}
