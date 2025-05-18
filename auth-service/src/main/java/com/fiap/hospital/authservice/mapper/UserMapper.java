package com.fiap.hospital.authservice.mapper;

import com.fiap.hospital.authservice.dto.RegisterRequest;
import com.fiap.hospital.authservice.dto.RegisterResponse;
import com.fiap.hospital.authservice.dto.UserInfoResponse;
import com.fiap.hospital.authservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Named("encodePassword")
    String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Mapping(target = "password", qualifiedByName = "encodePassword")
    public abstract User map(RegisterRequest registerRequest);

    public abstract RegisterResponse map(User user);

    public abstract UserInfoResponse mapUserInfo(User user);
}