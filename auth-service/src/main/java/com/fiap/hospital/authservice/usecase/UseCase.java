package com.fiap.hospital.authservice.usecase;

public interface UseCase<I, O> {
    O execute(I input);
}
