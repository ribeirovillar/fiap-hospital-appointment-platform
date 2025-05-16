package com.fiap.hospital.authservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.hospital.authservice.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {

    @NotBlank(message = "Name is required")
    @NotNull(message = "Name cannot be null")
    String name;

    @NotBlank(message = "Username is required")
    @NotNull(message = "Username cannot be null")
    String username;

    @NotBlank(message = "Password is required")
    @NotNull(message = "Password cannot be null")
    String password;

    @NotNull(message = "Role cannot be null")
    @JsonProperty(required = true)
    Role role;
}
