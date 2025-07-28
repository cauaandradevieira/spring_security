package com.br.spring_security.notification.exception;

import lombok.Getter;

@Getter
public enum EmailStatus
{
    MESSAGE_NOT_SENT,
    SMTP_AUTH_FAILED,
    INVALID_TEMPLATE,
    SMTP_SERVER_UNAVAILABLE;
}
