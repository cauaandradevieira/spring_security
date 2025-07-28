package com.br.spring_security.user.exception.enums;

import lombok.Getter;

@Getter
public enum UserErroStatus {
    USER_NOT_FOUND,
    USER_CONTAINS_ROLE,
    NO_USER_FOUND,
    USER_OR_ROLE_NOT_FOUND,
    UPDATING_USER_ROLE_FAILED
}
