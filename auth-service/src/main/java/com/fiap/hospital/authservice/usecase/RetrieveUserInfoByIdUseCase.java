package com.fiap.hospital.authservice.usecase;

import com.fiap.hospital.authservice.entity.User;
import com.fiap.hospital.authservice.exception.UserNotFoundException;
import com.fiap.hospital.authservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RetrieveUserInfoByIdUseCase {

    UserRepository userRepository;

    public User execute(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
    }
}
