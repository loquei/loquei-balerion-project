package com.loquei.core.infrastructure.email.event;

import com.loquei.common.event.EventListener;
import com.loquei.common.exceptions.InternalErrorException;
import com.loquei.core.domain.email.event.EmailEvent;
import jakarta.mail.MessagingException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailEventListener implements EventListener<EmailEvent> {

    private final JavaMailSender javaMailSender;

    public EmailEventListener(final JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void onEvent(final EmailEvent event) {
        try {
            final var message = javaMailSender.createMimeMessage();
            final var helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(event.getEmail().getRecipient());
            helper.setSubject(event.getEmail().getSubject());
            helper.setText(event.getEmail().getBody(), true);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw InternalErrorException.with("Failed to send email", e);
        }
    }

}
