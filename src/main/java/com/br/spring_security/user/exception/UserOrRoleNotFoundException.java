package com.br.spring_security.user.exception;

import lombok.Getter;

import java.util.UUID;

@Getter
public class UserOrRoleNotFoundException extends RuntimeException
{
    private final UUID userId;
    private final Integer roleId;
    public UserOrRoleNotFoundException(String message, UUID userId, Integer roleId)
    {
        super(message);
        this.userId = userId;
        this.roleId = roleId;
    }
}
