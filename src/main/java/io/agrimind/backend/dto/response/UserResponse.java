package io.agrimind.backend.dto.response;

import io.agrimind.backend.entity.Role;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Role role;
}