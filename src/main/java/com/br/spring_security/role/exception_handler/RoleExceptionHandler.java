package com.br.spring_security.role.exception_handler;

import com.br.spring_security.global.ErrorResponse;
import com.br.spring_security.role.exception.BasicRoleImmutableException;
import com.br.spring_security.role.exception.NoRoleFoundException;
import com.br.spring_security.role.exception.RoleEqualsException;
import com.br.spring_security.role.exception.enums.RoleErrorStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RoleExceptionHandler
{
    @ExceptionHandler(NoRoleFoundException.class)
    public ResponseEntity<ErrorResponse> noRoleFoundExceptionHandler(NoRoleFoundException ex)
    {
        log.error("No role registered in db", ex);

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                RoleErrorStatus.NO_ROLE_FOUND.toString(),
                ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(RoleEqualsException.class)
    public ResponseEntity<ErrorResponse> roleEqualsExceptionHandler(RoleEqualsException ex)
    {
        log.error("""
                first role with id: {}
                second role with id: {}
                """, ex.getFirstRoleId(),ex.getSecondRoleId(), ex);

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                RoleErrorStatus.ROLES_EQUALS.toString(),
                ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(BasicRoleImmutableException.class)
    public ResponseEntity<ErrorResponse> basicRoleImmutableExceptionHandler(BasicRoleImmutableException ex)
    {
        log.error("role basic cant'n have action.", ex);

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                RoleErrorStatus.ROLE_BASIC_IMMUTABLE.toString(),
                ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
