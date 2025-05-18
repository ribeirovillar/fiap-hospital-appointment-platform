package com.fiap.hospital.authservice.usecase.strategy.register;

import com.fiap.hospital.authservice.entity.User;

public interface RegisterStrategy {
    void execute(User user);
}
