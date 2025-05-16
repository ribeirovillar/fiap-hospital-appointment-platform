package com.fiap.hospital.authservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {
    @NotBlank(message = "Username is required")
    @NotNull(message = "Username cannot be null")
    String username;

    @NotBlank(message = "Password is required")
    @NotNull(message = "Password cannot be null")
    String password;
}
