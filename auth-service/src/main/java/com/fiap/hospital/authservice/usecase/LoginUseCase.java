package com.fiap.hospital.authservice.usecase;

import com.fiap.hospital.authservice.service.JwtService;
import com.fiap.hospital.authservice.dto.LoginRequest;
import com.fiap.hospital.authservice.dto.LoginResponse;
import com.fiap.hospital.authservice.exception.TokenGenerationException;
import com.fiap.hospital.authservice.repository.UserRepository;
import com.fiap.hospital.authservice.usecase.strategy.login.LoginStrategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class LoginUseCase {

    UserRepository userRepository;
    JwtService jwtService;
    List<LoginStrategy> loginStrategies;


    public LoginResponse execute(LoginRequest request) {
        loginStrategies.forEach(strategy -> strategy.execute(request));

        String token = userRepository.findByUsername(request.getUsername())
                .map(user -> jwtService.generateToken(user.getUsername(), user.getRole().name()))
                .orElseThrow(() -> new TokenGenerationException("Failed to generate token"));

        return new LoginResponse(token);
    }
}
