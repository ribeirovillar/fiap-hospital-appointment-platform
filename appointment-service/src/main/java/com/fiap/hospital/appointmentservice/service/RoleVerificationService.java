package com.fiap.hospital.appointmentservice.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RoleVerificationService {

    private static final String ROLE_PREFIX = "ROLE_";

    public boolean isRole(String role) {
        String formattedRole = role.toUpperCase().startsWith(ROLE_PREFIX) ? role.toUpperCase() : ROLE_PREFIX + role.toUpperCase();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .anyMatch(auth -> formattedRole.equals(auth.getAuthority()));
    }
}
