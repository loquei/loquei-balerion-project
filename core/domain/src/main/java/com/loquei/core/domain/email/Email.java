package com.loquei.core.domain.email;

import com.loquei.common.ValueObject;

public class Email extends ValueObject {

    private final String recipient;
    private final String subject;
    private final String body;

    public Email(final String recipient, final String subject, final String body) {
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
    }

    public static Email newEmail(final String recipient, final String subject, final String body) {
        return new Email(recipient, subject, body);
    }

    public static Email with(final String recipient, final String subject, final String body) {
        return new Email(recipient, subject, body);
    }

    public static Email with(final Email email) {
        return new Email(email.getRecipient(), email.getSubject(), email.getBody());
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }
}
