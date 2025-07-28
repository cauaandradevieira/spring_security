package com.br.spring_security.user.exception_handler;

import com.br.spring_security.global.ErrorResponse;
import com.br.spring_security.user.entity.User;
import com.br.spring_security.user.exception.NoUserFoundException;
import com.br.spring_security.user.exception.UserContainsRoleException;
import com.br.spring_security.user.exception.UserNotFoundException;
import com.br.spring_security.user.exception.UserOrRoleNotFoundException;
import com.br.spring_security.user.exception.enums.UserErroStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;

@Slf4j
@RestControllerAdvice
public class UserExceptionHandler
{
    @ExceptionHandler(UserContainsRoleException.class)
    public ResponseEntity<ErrorResponse> userContainsRoleExceptionHandler(UserContainsRoleException ex)
    {
        log.error("user with id: {} already contains a role with id: {} \n", ex.getUserId(), ex.getRoleId(), ex);

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                UserErroStatus.USER_CONTAINS_ROLE.toString(),
                ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundExceptionHandler(UserNotFoundException ex)
    {
        log.error("user with id: {} not found in the database\n", ex.getUserId(), ex);

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                UserErroStatus.USER_NOT_FOUND.toString(),
                ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(NoUserFoundException.class)
    public ResponseEntity<ErrorResponse> noUserFoundExceptionHandler(NoUserFoundException ex)
    {
        log.error("no user found in the database\n", ex);

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                UserErroStatus.NO_USER_FOUND.toString(),
                ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(UserOrRoleNotFoundException.class)
    public ResponseEntity<ErrorResponse> userOrRoleNotFoundExceptionHandler(UserOrRoleNotFoundException ex)
    {
        log.error("user with id: {}\n or role with id: {} not found in the database",ex.getUserId(), ex.getRoleId(), ex);

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                UserErroStatus.USER_OR_ROLE_NOT_FOUND.toString(),
                ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
