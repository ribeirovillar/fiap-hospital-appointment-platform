package com.fiap.hospital.authservice.usecase.strategy.register;

import com.fiap.hospital.authservice.dto.RegisterRequest;

public interface RegisterStrategy {
    void execute(RegisterRequest request);
}
