package com.br.spring_security.auth.exception_handler;

import com.br.spring_security.auth.exception.EmailAlreadyInUseException;
import com.br.spring_security.auth.exception.enums.AuthErrorStatus;
import com.br.spring_security.global.ErrorResponse;
import com.br.spring_security.user.exception.UserContainsRoleException;
import com.br.spring_security.user.exception.enums.UserErroStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<ErrorResponse> emailAlreadyInUseExceptionHandler(EmailAlreadyInUseException ex)
    {
        log.warn("The user tried to register in the system with an email: {} already in use.\n",ex.getEmail() ,ex);

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                AuthErrorStatus.EMAIL_ALREADY_IN_USE.toString(),
                ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
