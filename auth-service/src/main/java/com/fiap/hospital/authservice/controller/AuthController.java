package com.fiap.hospital.authservice.controller;

import com.fiap.hospital.authservice.dto.LoginRequest;
import com.fiap.hospital.authservice.dto.LoginResponse;
import com.fiap.hospital.authservice.dto.RegisterRequest;
import com.fiap.hospital.authservice.dto.RegisterResponse;
import com.fiap.hospital.authservice.usecase.LoginUseCase;
import com.fiap.hospital.authservice.usecase.RegisterUseCase;
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

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(loginUseCase.execute(request));
    }

}
