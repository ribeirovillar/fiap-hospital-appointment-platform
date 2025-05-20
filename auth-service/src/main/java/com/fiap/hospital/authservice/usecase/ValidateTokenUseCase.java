package com.fiap.hospital.authservice.usecase;

import com.fiap.hospital.authservice.service.JwtService;
import com.fiap.hospital.authservice.dto.TokenValidationRequest;
import com.fiap.hospital.authservice.dto.TokenValidationResponse;
import com.fiap.hospital.authservice.enums.Role;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class ValidateTokenUseCase {

    JwtService jwtService;

    public TokenValidationResponse execute(TokenValidationRequest request) {
        String token = request.getToken();

        if (!jwtService.isTokenValid(token)) {
            return new TokenValidationResponse(null, null, false);
        }

        String username = jwtService.extractUsername(token);
        Claims claims = jwtService.extractAllClaims(token);
        String role = claims.get("role", String.class);

        return new TokenValidationResponse(username, Role.valueOf(role), true);
    }
}
