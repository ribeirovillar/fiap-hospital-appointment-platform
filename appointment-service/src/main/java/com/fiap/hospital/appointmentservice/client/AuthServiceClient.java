package com.fiap.hospital.appointmentservice.client;

import com.fiap.hospital.appointmentservice.client.dto.UserInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service", url = "${auth.service.base-url}")
public interface AuthServiceClient {

    @GetMapping("/users/{userId}")
    UserInfoResponse getUserInfoById(@PathVariable("userId") Long userId);

}
