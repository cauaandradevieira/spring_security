package com.br.spring_security.role.exception;

public class NoRoleFoundException extends RuntimeException {
    public NoRoleFoundException(String message) {
        super(message);
    }
}
