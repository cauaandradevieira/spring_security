package com.br.spring_security.user.exception;

import lombok.Getter;

import java.util.UUID;

@Getter
public class UserContainsRoleException extends RuntimeException
{
    private final Integer roleId;
    private final UUID userId;
    public UserContainsRoleException(String message, UUID userId, Integer roleId)
    {
        super(message);
        this.userId = userId;
        this.roleId = roleId;
    }
}
