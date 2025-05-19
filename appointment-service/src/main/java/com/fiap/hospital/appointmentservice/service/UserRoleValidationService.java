package com.fiap.hospital.appointmentservice.service;

import com.fiap.hospital.appointmentservice.client.AuthServiceClient;
import com.fiap.hospital.appointmentservice.client.dto.UserInfoResponse;
import com.fiap.hospital.appointmentservice.exception.ParticipantNotFoundException;
import com.fiap.hospital.appointmentservice.exception.UserRoleNotMatchException;
import feign.FeignException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserRoleValidationService {

    AuthServiceClient authServiceClient;

    public void validateUserIdByRole(Long userId, String expectedRole) {
        UserInfoResponse response;

        try {
            response = authServiceClient.getUserInfoById(userId);
        } catch (FeignException ex) {
            if (ex.status() == HttpStatus.NOT_FOUND.value()) {
                throw new ParticipantNotFoundException("User not found with id: " + userId + " and role: " + expectedRole);
            }
            throw new RuntimeException("Error retrieving user info: " + ex.getMessage(), ex);
        }

        if (response == null || !expectedRole.equalsIgnoreCase(response.getRole())) {
            throw new UserRoleNotMatchException(
                    "User ID " + userId + " does not match expected role: " + expectedRole);
        }
    }
}
