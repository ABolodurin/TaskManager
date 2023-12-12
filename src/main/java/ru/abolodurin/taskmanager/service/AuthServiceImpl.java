package ru.abolodurin.taskmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.abolodurin.taskmanager.model.dto.AuthResponse;
import ru.abolodurin.taskmanager.model.dto.LoginRequest;
import ru.abolodurin.taskmanager.model.dto.RegisterRequest;
import ru.abolodurin.taskmanager.model.entity.CommonException;
import ru.abolodurin.taskmanager.model.entity.Role;
import ru.abolodurin.taskmanager.model.entity.User;
import ru.abolodurin.taskmanager.security.JwtService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userService.isExist(request.getUsername())) throw new CommonException(
                "user " + request.getUsername() + " is already exist");

        User user = User
                .builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userService.add(user);

        return getResponseFor(user);
    }

    @Override
    public AuthResponse auth(LoginRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()));

        return getResponseFor(userService.findByUsername(request.getUsername()));
    }

    private AuthResponse getResponseFor(User user) {
        String token = jwtService.generateToken(user);

        return AuthResponse.of(token);
    }

}
