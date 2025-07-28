package com.br.spring_security.auth.controller;

import com.br.spring_security.auth.dto.request.ConfirmRegistrationRequest;
import com.br.spring_security.auth.dto.request.LoginRequestDTO;
import com.br.spring_security.auth.dto.request.RegisterRequestDTO;
import com.br.spring_security.auth.dto.response.JwtReponseDTO;
import com.br.spring_security.auth.service.AuthService;
import com.br.spring_security.cookie.enums.CookieType;
import com.br.spring_security.cookie.service.ICookieService;
import com.br.spring_security.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController
{
    private final AuthService authService;
    private final ICookieService cookieService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO request)
    {
        String pendingUserEmail = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(pendingUserEmail);
    }

    @PostMapping("/login")
    private ResponseEntity<?> login(@RequestBody LoginRequestDTO request)
    {
        ResponseCookie cookie = cookieService.newCookie(CookieType.REFRESH);
        JwtReponseDTO jwtReponseDTO = authService.login(request);

        log.info("my cookie {}", cookie.toString());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(jwtReponseDTO);
    }

    @PostMapping("/register/confirm")
    private ResponseEntity<?> confirmRegistration(@RequestBody @Valid ConfirmRegistrationRequest request)
    {
        authService.confirmRegistration(request);
        return ResponseEntity.ok().build();
    }

}
