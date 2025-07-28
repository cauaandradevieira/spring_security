package com.br.spring_security.cookie.service.imp;

import com.br.spring_security.cookie.service.ICookieService;
import com.br.spring_security.cookie.enums.CookieType;
import com.br.spring_security.utils.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CookieService implements ICookieService
{
    private final CodeGenerator codeGenerator;

    @Override
    public ResponseCookie newCookie(CookieType cookie) {
        String code = codeGenerator.generateCodeForCookies();

        return ResponseCookie.from(cookie.getName(), code)
                .httpOnly(cookie.getHttpOnly())
                .path(cookie.getPath())
                .maxAge(cookie.getDuration())
                .sameSite(cookie.getSameSite())
                .build();
    }

    @Override
    public ResponseCookie deleteCookie(CookieType typeCookie) {
        return null;
    }
}
