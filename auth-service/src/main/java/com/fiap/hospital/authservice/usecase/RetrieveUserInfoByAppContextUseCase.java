package com.fiap.hospital.authservice.usecase;

import com.fiap.hospital.authservice.entity.User;
import com.fiap.hospital.authservice.exception.UserNotFoundException;
import com.fiap.hospital.authservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RetrieveUserInfoByAppContextUseCase {
    UserRepository userRepository;

    public User execute() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(principal.getUsername()).orElseThrow(() -> new UserNotFoundException("User not found with username: " + principal.getUsername()));
    }
}
