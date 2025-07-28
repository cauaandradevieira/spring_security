package com.br.spring_security.notification.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmailSenderRequest(
        @NotBlank
        @NotNull
        String to,
        @NotBlank
        @NotNull
        String title,
        @NotNull
        @NotBlank
        String body) {
}
