package com.br.spring_security.cookie.service;

import com.br.spring_security.cookie.enums.CookieType;
import org.springframework.http.ResponseCookie;

public interface ICookieService
{
    ResponseCookie newCookie(CookieType type);
    ResponseCookie deleteCookie(CookieType typeCookie);
}
