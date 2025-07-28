package com.br.spring_security.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ConfirmRegistrationRequest(
        @Email
        @NotNull
        @NotBlank
        String email,

        @NotNull
        @NotBlank
        String confirmationCode) {
}
