package com.br.spring_security.notification.exception;

import lombok.Getter;

@Getter
public class FailedToSendEmailException extends RuntimeException {

    private final String to;
    public FailedToSendEmailException(String message, String to, Throwable e)
    {
        super(message, e);
        this.to = to;
    }
}
