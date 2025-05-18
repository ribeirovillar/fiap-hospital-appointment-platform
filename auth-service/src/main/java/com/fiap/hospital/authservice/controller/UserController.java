package com.fiap.hospital.authservice.controller;

import com.fiap.hospital.authservice.dto.UserInfoResponse;
import com.fiap.hospital.authservice.mapper.UserMapper;
import com.fiap.hospital.authservice.usecase.GetUserInfoByIdUseCase;
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

    GetUserInfoByIdUseCase getUserInfoByIdUseCase;
    UserMapper userMapper;

    @GetMapping("/{userId}")
    public ResponseEntity<UserInfoResponse> getUserInfo(@PathVariable Long userId) {
        return ResponseEntity.ok(userMapper.mapUserInfo(getUserInfoByIdUseCase.execute(userId)));
    }

}
