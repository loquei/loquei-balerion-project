package com.loquei.core.infrastructure.email;

import com.loquei.core.domain.email.Email;
import com.loquei.core.domain.email.EmailGateway;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SecuritySpringEmailGateway implements EmailGateway {

    private final JavaMailSender javaMailSender;

    public SecuritySpringEmailGateway(final JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void send(final Email email) {
        final var message = new SimpleMailMessage();
        message.setTo(email.getRecipient());
        message.setSubject(email.getSubject());
        message.setText(email.getBody());

        javaMailSender.send(message);
    }
}
