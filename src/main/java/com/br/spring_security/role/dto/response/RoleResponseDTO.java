package com.br.spring_security.role.dto.response;

import com.br.spring_security.role.entity.Role;

public record RoleResponseDTO(Integer id, String name)
{
    public RoleResponseDTO(Role role)
    {
        this(role.getId(),role.getName());
    }
}
