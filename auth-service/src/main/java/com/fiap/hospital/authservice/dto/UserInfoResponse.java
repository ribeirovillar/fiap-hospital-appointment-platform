package com.fiap.hospital.authservice.dto;

import com.fiap.hospital.authservice.enums.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserInfoResponse {
    Long id;
    String name;
    Role role;
}
