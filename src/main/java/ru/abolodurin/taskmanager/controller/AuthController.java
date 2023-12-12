package ru.abolodurin.taskmanager.controller;

import org.springframework.http.ResponseEntity;
import ru.abolodurin.taskmanager.model.dto.AuthResponse;
import ru.abolodurin.taskmanager.model.dto.LoginRequest;
import ru.abolodurin.taskmanager.model.dto.RegisterRequest;

public interface AuthController {
    ResponseEntity<AuthResponse> register(RegisterRequest request);

    ResponseEntity<AuthResponse> login(LoginRequest request);

}
