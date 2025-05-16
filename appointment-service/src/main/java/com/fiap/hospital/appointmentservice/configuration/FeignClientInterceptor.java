package com.fiap.hospital.appointmentservice.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FeignClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getCredentials() != null) {
            String token = authentication.getCredentials().toString();
            if (!token.startsWith("Bearer ")) {
                token = "Bearer " + token;
            }
            template.header("Authorization", token);
            log.debug("Added Authorization header with token to Feign request");
        } else {
            log.debug("No Authentication or token found in SecurityContext");
        }
    }
}
