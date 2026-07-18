package io.agrimind.backend.controller;

import io.agrimind.backend.dto.request.LoginRequest;
import io.agrimind.backend.dto.request.RegisterRequest;
import io.agrimind.backend.dto.response.UserResponse;
import io.agrimind.backend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.agrimind.backend.dto.response.LoginResponse;
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@Valid @RequestBody RegisterRequest request) {

        return authService.register(request);

    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {

        return authService.login(request);

    }

}