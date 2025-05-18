package com.fiap.hospital.authservice.usecase;

import com.fiap.hospital.authservice.entity.User;
import com.fiap.hospital.authservice.exception.UserRegistrationException;
import com.fiap.hospital.authservice.repository.UserRepository;
import com.fiap.hospital.authservice.usecase.strategy.register.RegisterStrategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RegisterUseCase {

    UserRepository userRepository;
    List<RegisterStrategy> registerStrategies;

    public User execute(User user) {
        registerStrategies.forEach(strategy -> strategy.execute(user));
        return Optional.of(userRepository.save(user))
                .orElseThrow(() -> new UserRegistrationException("User registration failed"));
    }
}
