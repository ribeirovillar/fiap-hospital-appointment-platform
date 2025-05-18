package com.fiap.hospital.authservice.usecase.strategy.register;

import com.fiap.hospital.authservice.entity.User;
import com.fiap.hospital.authservice.exception.UserNameInUseException;
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
    public void execute(User user) {
        userRepository.findByUsername(user.getUsername()).ifPresent(u -> {
            throw new UserNameInUseException("Username already in use: " + u.getUsername());
        });
    }
}
