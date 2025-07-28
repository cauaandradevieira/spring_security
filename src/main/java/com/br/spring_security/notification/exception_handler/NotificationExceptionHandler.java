package com.br.spring_security.notification.exception_handler;

import com.br.spring_security.global.ErrorResponse;
import com.br.spring_security.notification.exception.EmailStatus;
import com.br.spring_security.notification.exception.FailedToSendEmailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.thymeleaf.exceptions.TemplateInputException;

@Slf4j
@RestControllerAdvice
public class NotificationExceptionHandler
{
    @ExceptionHandler(FailedToSendEmailException.class)
    public ResponseEntity<?> failedToSendExceptionHandler(FailedToSendEmailException ex)
    {
        log.error("Error to send the email: for: {} \n",
                ex.getTo(),
                ex);

        ErrorResponse response = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                EmailStatus.MESSAGE_NOT_SENT.toString(),
                ex.getMessage()
        );

        return ResponseEntity.internalServerError().body(response);
    }

    @ExceptionHandler(MailAuthenticationException.class)
    public ResponseEntity<?> mailAuthenticationExceptionHandler(MailAuthenticationException ex)
    {
        log.error("SMTP authentication failed:\n", ex);

        ErrorResponse response = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                EmailStatus.SMTP_AUTH_FAILED.toString(),
                "Erro no envio de mensagem."
        );

        return ResponseEntity.internalServerError().body(response);
    }

    @ExceptionHandler(MailSendException.class)
    public ResponseEntity<?> mailSendHandlerExceptionHandler(MailSendException  ex)
    {
        log.error("Failure to send by the SMTP server:\n", ex);

        ErrorResponse response = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                EmailStatus.SMTP_SERVER_UNAVAILABLE.toString(),
                "Erro no envio de mensagem."
        );

        return ResponseEntity.internalServerError().body(response);
    }

    @ExceptionHandler(TemplateInputException.class)
    public ResponseEntity<?> TemplateInputExceptionHandler(TemplateInputException   ex)
    {
        log.error("invalid template:\n", ex);

        ErrorResponse response = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                EmailStatus.INVALID_TEMPLATE.toString(),
                "Erro no envio de mensagem."
        );

        return ResponseEntity.internalServerError().body(response);
    }

}
