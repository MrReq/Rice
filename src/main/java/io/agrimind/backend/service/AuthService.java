package io.agrimind.backend.service;

import io.agrimind.backend.dto.request.LoginRequest;
import io.agrimind.backend.dto.request.RegisterRequest;
import io.agrimind.backend.dto.response.LoginResponse;
import io.agrimind.backend.dto.response.UserResponse;
import io.agrimind.backend.security.CustomUserDetailsService;
import io.agrimind.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public UserResponse register(RegisterRequest request) {
        return userService.registerUser(request);
    }

    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String token = jwtService.generateToken(userDetails);

        return LoginResponse.builder()
                .token(token)
                .type("Bearer")
                .build();
    }
}