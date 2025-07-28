package com.br.spring_security.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record LoginRequestDTO (
        @Email
        @NotBlank
        @NotNull
        String email,

        @NotBlank
        @Pattern(regexp = "^[A-Za-z0-9]+$")
        String password
        ){
}
