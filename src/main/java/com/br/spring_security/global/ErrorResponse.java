package com.br.spring_security.global;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter @Setter
public class ErrorResponse
{
    private Integer status;
    private String code;
    private String message;
    private Instant date;

    public ErrorResponse(Integer status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.date = Instant.now();
    }
}
