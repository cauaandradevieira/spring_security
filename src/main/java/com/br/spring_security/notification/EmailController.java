package com.br.spring_security.notification;

import com.br.spring_security.notification.dto.EmailSenderRequest;
import com.br.spring_security.notification.sender.EmailSender;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController
{
    private final EmailSender emailSender;

    @PostMapping("/send")
    public void send(@RequestBody @Valid EmailSenderRequest request)
    {
        emailSender.send(request);
    }
}
