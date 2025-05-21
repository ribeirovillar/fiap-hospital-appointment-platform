package com.fiap.hospital.historyservice.client.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserInfoResponse {
    Long id;
    String name;
    String role;
}
