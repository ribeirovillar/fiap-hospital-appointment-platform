package com.fiap.hospital.authservice.controller;

import com.fiap.hospital.authservice.dto.*;
import com.fiap.hospital.authservice.mapper.UserMapper;
import com.fiap.hospital.authservice.usecase.LoginUseCase;
import com.fiap.hospital.authservice.usecase.RegisterUseCase;
import com.fiap.hospital.authservice.usecase.ValidateTokenUseCase;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthController {

    LoginUseCase loginUseCase;
    RegisterUseCase registerUseCase;
    ValidateTokenUseCase validateTokenUseCase;
    UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        var user = userMapper.map(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.map(registerUseCase.execute(user)));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(loginUseCase.execute(request));
    }

    @PostMapping("/validate-token")
    public ResponseEntity<TokenValidationResponse> validateToken(@RequestBody @Valid TokenValidationRequest request) {
        TokenValidationResponse response = validateTokenUseCase.execute(request);
        return ResponseEntity.ok(response);
    }


}
