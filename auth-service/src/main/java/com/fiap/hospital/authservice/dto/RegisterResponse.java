package com.fiap.hospital.authservice.dto;

import com.fiap.hospital.authservice.enums.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterResponse {
    Long id;
    String name;
    String username;
    Role role;
}
