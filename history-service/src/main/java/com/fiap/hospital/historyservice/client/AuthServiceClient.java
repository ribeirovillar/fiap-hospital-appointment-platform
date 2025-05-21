package com.fiap.hospital.historyservice.client;


import com.fiap.hospital.historyservice.client.dto.TokenValidationRequest;
import com.fiap.hospital.historyservice.client.dto.TokenValidationResponse;
import com.fiap.hospital.historyservice.client.dto.UserInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth-service", url = "${auth.service.base-url}")
public interface AuthServiceClient {

    @GetMapping("/users/{userId}")
    UserInfoResponse getUserInfoById(@PathVariable("userId") Long userId);

    @PostMapping("/auth/validate-token")
    TokenValidationResponse validateToken(@RequestBody TokenValidationRequest request);

}
