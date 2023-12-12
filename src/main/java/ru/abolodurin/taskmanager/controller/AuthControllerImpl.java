package ru.abolodurin.taskmanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.abolodurin.taskmanager.configuration.SwaggerConfig;
import ru.abolodurin.taskmanager.model.dto.AuthResponse;
import ru.abolodurin.taskmanager.model.dto.LoginRequest;
import ru.abolodurin.taskmanager.model.dto.RegisterRequest;
import ru.abolodurin.taskmanager.service.AuthService;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name = SwaggerConfig.AUTH_TAG)
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;

    @Override
    @PostMapping("/register")
    @Operation(summary = "Receives registration form and returns bearer token",
            responses = @ApiResponse(responseCode = "200", description = "Successful registration"))
    public @ResponseBody ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @Override
    @PostMapping("/login")
    @Operation(summary = "Receives login form and returns bearer token",
            responses = @ApiResponse(responseCode = "200", description = "Successful login"))
    public @ResponseBody ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.auth(request));
    }

}
