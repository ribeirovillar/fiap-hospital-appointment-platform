package com.fiap.hospital.authservice.controller;

import com.fiap.hospital.authservice.dto.UserInfoResponse;
import com.fiap.hospital.authservice.mapper.UserMapper;
import com.fiap.hospital.authservice.usecase.RetrieveUserInfoByAppContextUseCase;
import com.fiap.hospital.authservice.usecase.RetrieveUserInfoByIdUseCase;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {

    RetrieveUserInfoByIdUseCase retrieveUserInfoByIdUseCase;
    RetrieveUserInfoByAppContextUseCase retrieveUserInfoByAppContextUseCase;
    UserMapper userMapper;

    @GetMapping("/{userId}")
    public ResponseEntity<UserInfoResponse> getUserInfo(@PathVariable Long userId) {
        return ResponseEntity.ok(userMapper.mapUserInfo(retrieveUserInfoByIdUseCase.execute(userId)));
    }

    @GetMapping("/me")
    public ResponseEntity<UserInfoResponse> getCurrentUserInfo() {
        return ResponseEntity.ok(userMapper.mapUserInfo(retrieveUserInfoByAppContextUseCase.execute()));
    }

}
