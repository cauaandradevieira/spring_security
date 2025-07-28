package com.br.spring_security.utils;

import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
public class CodeGenerator
{
    public String generateSixDigitCode()
    {
        String code = String.format("%06d", new Random().nextInt(999999));
        validateCodeSixDigits(code);
        return code;
    }

    public String generateCodeForCookies()
    {
        return String.valueOf(UUID.randomUUID());
    }

    private void validateCodeSixDigits(String code)
    {
        if(code.isBlank() || code.length() != 6)
        {
            throw new IllegalArgumentException("Erro ao gerar o código, tente mais tarde.");
        }
    }
}
