package io.agrimind.backend.mapper;

import io.agrimind.backend.dto.request.RegisterRequest;
import io.agrimind.backend.dto.response.UserResponse;
import io.agrimind.backend.entity.Role;
import io.agrimind.backend.entity.User;

public final class UserMapper {

    private UserMapper() {}

    public static User toEntity(RegisterRequest request) {
        if (request == null) {
            return null;
        }

        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(Role.USER)
                .build();
    }

    public static UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }

        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}