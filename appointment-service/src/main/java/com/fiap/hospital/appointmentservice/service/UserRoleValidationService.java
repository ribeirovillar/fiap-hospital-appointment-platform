package com.fiap.hospital.appointmentservice.service;

import com.fiap.hospital.appointmentservice.client.AuthServiceClient;
import com.fiap.hospital.appointmentservice.exception.UserRoleNotMatchException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserRoleValidationService {

    AuthServiceClient authServiceClient;

    public void validateUserIdByRole(Long userId, String expectedRole) {

        var response = authServiceClient.getUserInfoById(userId);

        if (response == null || !expectedRole.equalsIgnoreCase(response.getRole())) {
            throw new UserRoleNotMatchException(
                    "User ID " + userId + " does not match expected role: " + expectedRole);
        }
    }
}
