package com.br.spring_security.user.dto.response;

import com.br.spring_security.role.dto.response.RoleResponseDTO;
import com.br.spring_security.user.entity.User;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public record UserWithRoleResponse(
        UUID id,
        String email,
        String name,
        Boolean enabled,
        Instant dateCreatedAt,
        Instant dateUpdatedAt,
        Set<RoleResponseDTO> roles) {

    public UserWithRoleResponse(User user)
    {
        this(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getIsEnable(),
                user.getDateCreatedAt(),
                user.getDateUpdatedAt(),
                user.getRoles().stream()
                        .map(RoleResponseDTO::new)
                        .collect(Collectors.toSet())
        );
    }
}
