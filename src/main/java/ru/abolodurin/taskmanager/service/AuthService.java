package ru.abolodurin.taskmanager.service;

import ru.abolodurin.taskmanager.model.dto.AuthResponse;
import ru.abolodurin.taskmanager.model.dto.LoginRequest;
import ru.abolodurin.taskmanager.model.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);

    AuthResponse auth(LoginRequest request);

}
