package com.fiap.hospital.authservice.usecase.strategy.login;

import com.fiap.hospital.authservice.dto.LoginRequest;
import com.fiap.hospital.authservice.exception.InvalidPasswordException;
import com.fiap.hospital.authservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PasswordValidation implements LoginStrategy {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public void execute(LoginRequest request) {
        userRepository.findByUsername(request.getUsername()).ifPresent(user -> {
            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                throw new InvalidPasswordException("Invalid password");
            }
        });
    }
}
