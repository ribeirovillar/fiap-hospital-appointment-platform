package com.fiap.hospital.authservice.usecase.strategy.register;

import com.fiap.hospital.authservice.exception.UserNameInUseException;
import com.fiap.hospital.authservice.dto.RegisterRequest;
import com.fiap.hospital.authservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserNameValidation implements RegisterStrategy {

    UserRepository userRepository;

    @Override
    public void execute(RegisterRequest request) {
        userRepository.findByUsername(request.getUsername()).ifPresent(user -> {
            throw new UserNameInUseException("Username already in use: " + user.getUsername());
        });
    }
}
