package com.loquei.core.domain.email.event;

import com.loquei.common.event.Event;
import com.loquei.core.domain.email.Email;

public class EmailEvent extends Event {

    private final Email email;

    public EmailEvent(final Email email) {
        this.email = email;
    }

    public Email getEmail() {
        return email;
    }

}
