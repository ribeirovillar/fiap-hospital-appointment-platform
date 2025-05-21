package com.fiap.hospital.historyservice.configuration;

import com.fiap.hospital.historyservice.client.AuthServiceClient;
import com.fiap.hospital.historyservice.client.dto.TokenValidationRequest;
import com.fiap.hospital.historyservice.client.dto.TokenValidationResponse;
import feign.FeignException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class JwtAuthFilter extends OncePerRequestFilter {

    AuthServiceClient authServiceClient;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing or invalid Authorization header");
            return;
        }

        final String token = authHeader.substring(7);

        try {
            TokenValidationRequest validationRequest = new TokenValidationRequest();
            validationRequest.setToken(token);

            TokenValidationResponse result = authServiceClient.validateToken(validationRequest);

            if (result == null || !result.isValid()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid token");
                return;
            }

            var authenticatedUser = new AuthenticatedUser(
                    result.getId(),
                    result.getUsername(),
                    result.getRole()
            );

            var authToken = new UsernamePasswordAuthenticationToken(
                    authenticatedUser,
                    token,
                    List.of(new SimpleGrantedAuthority("ROLE_" + result.getRole()))
            );

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);

        } catch (FeignException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token validation failed");
            return;
        }

        filterChain.doFilter(request, response);
    }
}