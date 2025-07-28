package com.br.spring_security.user.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record UserRoleRequestDTO(
        @NotNull
        UUID id,

        @NotNull
        @Positive
        Integer newRoleId
) {
}
