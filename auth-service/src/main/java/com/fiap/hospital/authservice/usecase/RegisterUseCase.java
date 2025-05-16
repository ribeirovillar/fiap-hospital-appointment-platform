package com.fiap.hospital.authservice.usecase;

import com.fiap.hospital.authservice.dto.RegisterRequest;
import com.fiap.hospital.authservice.dto.RegisterResponse;
import com.fiap.hospital.authservice.entity.User;
import com.fiap.hospital.authservice.exception.UserRegistrationException;
import com.fiap.hospital.authservice.repository.UserRepository;
import com.fiap.hospital.authservice.usecase.strategy.register.RegisterStrategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RegisterUseCase implements UseCase<RegisterRequest, RegisterResponse> {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    List<RegisterStrategy> registerStrategies;

    @Override
    public RegisterResponse execute(RegisterRequest request) {
        registerStrategies.forEach(strategy -> strategy.execute(request));

        User user = User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        return Optional.of(userRepository.save(user)).map(userSaved -> RegisterResponse
                        .builder()
                        .id(userSaved.getId())
                        .name(userSaved.getName())
                        .username(userSaved.getUsername())
                        .role(userSaved.getRole())
                        .build())
                .orElseThrow(() -> new UserRegistrationException("User registration failed"));
    }
}
