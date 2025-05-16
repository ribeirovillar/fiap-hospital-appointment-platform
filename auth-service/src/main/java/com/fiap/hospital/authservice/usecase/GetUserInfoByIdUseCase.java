package com.fiap.hospital.authservice.usecase;

import com.fiap.hospital.authservice.dto.UserInfoResponse;
import com.fiap.hospital.authservice.entity.User;
import com.fiap.hospital.authservice.enums.Role;
import com.fiap.hospital.authservice.exception.UserNotFoundException;
import com.fiap.hospital.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetUserInfoByIdUseCase implements UseCase<Long, UserInfoResponse> {

    private final UserRepository userRepository;

    @Override
    public UserInfoResponse execute(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        return UserInfoResponse
                .builder()
                .name(user.getName())
                .role(Role.valueOf(user.getRole().name()))
                .build();
    }
}
