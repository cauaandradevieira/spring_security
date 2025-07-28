package com.br.spring_security.notification.sender;

import com.br.spring_security.notification.dto.EmailSenderRequest;
import com.br.spring_security.notification.exception.FailedToSendEmailException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailSender
{
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public void send(EmailSenderRequest request)
    {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(request.to());
        simpleMailMessage.setSubject(request.title());
        simpleMailMessage.setText(request.body());

        javaMailSender.send(simpleMailMessage);
    }

    public void send(@NotNull @NotBlank String to,
                     @NotNull @NotBlank String title,
                     @NotNull @NotBlank String body)
    {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(title);
        simpleMailMessage.setText(body);

        javaMailSender.send(simpleMailMessage);
    }

    public void sendRegistrationVerificationCode(@NotNull @NotBlank String to,
                                          @NotNull @NotBlank String code,
                                          @NotNull @NotBlank String name,
                                          @NotNull Instant dateExpiration)
    {
        try
        {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage,"UTF-8");

            Context context = new Context();
            context.setVariable("name", name);
            context.setVariable("code", code);
            context.setVariable("validade", ChronoUnit.HOURS.between(Instant.now(), dateExpiration));

            String html = templateEngine.process("message-for-pending-user", context);

            message.setTo(to);
            message.setSubject("Confirme seu código de verificação");
            message.setText(html, true);

            javaMailSender.send(mimeMessage);
        }

        catch (MessagingException e)
        {
            throw new FailedToSendEmailException("Não foi possivel enviar o email de verificação de email", to, e);
        }
    }

    public void sendRegistrationConfirm(@NotNull @NotBlank String to,
                                        @NotNull @NotBlank String name)
    {
        try
        {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

            Context context = new Context();
            context.setVariable("name", name);
            context.setVariable("appName", "ApplicationTest");

            String html = templateEngine.process("message-for-registration-confirmation", context);

            helper.setTo(to);
            helper.setSubject("Confirmação com Sucesso");
            helper.setText(html, true);

            javaMailSender.send(mimeMessage);
        }
        catch (MessagingException e)
        {
            throw new FailedToSendEmailException("Não foi possivel enviar o email de confirmação do registro", to, e);
        }

    }
}
