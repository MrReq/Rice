package io.agrimind.backend.service;

import io.agrimind.backend.dto.response.UserResponse;
import io.agrimind.backend.dto.request.RegisterRequest;

import io.agrimind.backend.exception.UserAlreadyExistsException;
import io.agrimind.backend.mapper.UserMapper;
import io.agrimind.backend.entity.User;
import io.agrimind.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserResponse registerUser(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException(request.getEmail());
        }

        User user = UserMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = userRepository.save(user);

        return UserMapper.toResponse(savedUser);
    }

}