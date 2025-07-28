package com.br.spring_security.cookie.enums;

import lombok.Getter;

import java.time.Duration;

@Getter
public enum CookieType {
    REFRESH("Refresh",
            "Strict",
            "/",
            true,
            Duration.ofMinutes(10)
    );

    CookieType(String name, String sameSite, String path, Boolean httpOnly, Duration duration) {

        this.name = name;
        this.sameSite = sameSite;
        this.path = path;
        this.httpOnly = httpOnly;
        this.duration = duration;
    }

    private final String name;
    private final String sameSite;
    private final String path;
    private final Boolean httpOnly;
    private final Duration duration;
}
