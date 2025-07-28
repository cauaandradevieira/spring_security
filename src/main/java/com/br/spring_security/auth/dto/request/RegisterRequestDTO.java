package com.br.spring_security.auth.dto.request;

import jakarta.validation.constraints.*;

public record RegisterRequestDTO(
        @Email
        @NotBlank
        @NotNull
        String email,

        @NotBlank
        @Pattern(regexp = "^[A-Za-z0-9.!%#]+$")
        String password,

        @NotBlank
        @NotNull
        String name)
{
}
