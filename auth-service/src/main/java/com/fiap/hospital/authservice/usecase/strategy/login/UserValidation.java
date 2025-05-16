package com.fiap.hospital.authservice.usecase.strategy.login;

import com.fiap.hospital.authservice.dto.LoginRequest;
import com.fiap.hospital.authservice.exception.UserNotFoundException;
import com.fiap.hospital.authservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserValidation implements LoginStrategy {

    UserRepository userRepository;

    @Override
    public void execute(LoginRequest request) {
        userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + request.getUsername()));
    }
}
