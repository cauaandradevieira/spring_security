package com.br.spring_security.role.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RoleRequestDTO (@NotBlank(message = "nome não pode ser vazio")
                              String name){
}
