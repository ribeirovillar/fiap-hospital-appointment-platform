package com.fiap.hospital.authservice.usecase;

import com.fiap.hospital.authservice.dto.TokenValidationRequest;
import com.fiap.hospital.authservice.dto.TokenValidationResponse;
import com.fiap.hospital.authservice.exception.UserNotFoundException;
import com.fiap.hospital.authservice.repository.UserRepository;
import com.fiap.hospital.authservice.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class ValidateTokenUseCase {

    JwtService jwtService;
    UserRepository userRepository;

    public TokenValidationResponse execute(TokenValidationRequest request) {
        String token = request.getToken();

        if (!jwtService.isTokenValid(token)) {
            return new TokenValidationResponse(null, null, null, false);
        }

        String username = jwtService.extractUsername(token);
        var user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found"));

        return new TokenValidationResponse(user.getId(), username, user.getRole(), true);
    }
}
