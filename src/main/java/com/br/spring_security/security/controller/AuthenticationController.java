package com.br.spring_security.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping
public class AuthenticationController
{
    @GetMapping("{message}")
    public Map<String, String> showMessage(@PathVariable String message)
    {
        return Map.of("teste",message);
    }
}
