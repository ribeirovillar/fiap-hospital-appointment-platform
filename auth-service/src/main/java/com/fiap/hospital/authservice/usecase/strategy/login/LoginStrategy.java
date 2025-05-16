package com.fiap.hospital.authservice.usecase.strategy.login;

import com.fiap.hospital.authservice.dto.LoginRequest;

public interface LoginStrategy {
    void execute(LoginRequest request);
}
