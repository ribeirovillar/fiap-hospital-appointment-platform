package com.fiap.hospital.authservice.configuration;

import com.fiap.hospital.authservice.exception.InvalidPasswordException;
import com.fiap.hospital.authservice.exception.UserNameInUseException;
import com.fiap.hospital.authservice.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            IllegalArgumentException.class,
            UserNameInUseException.class,
            InvalidPasswordException.class
    })
    public ResponseEntity<?> handleExceptions(Exception ex) {
        return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        if (ex.getMessage() != null && ex.getMessage().contains("Enum class")) {
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        }

        return ResponseEntity.badRequest().body(Map.of("error", "Invalid request format"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneric(Exception ex) {
        return ResponseEntity.status(500).body(Map.of("error", "Internal error: " + ex.getMessage()));
    }
}
