package com.loquei.core.application.user.update;

import java.time.LocalDate;

public record UpdateUserCommand(
        String id, String userName, String personalName, String email, String phone, String document, LocalDate birth) {

    public static UpdateUserCommand with(
            final String id,
            final String userName,
            final String personalName,
            final String email,
            final String phone,
            final String document,
            final LocalDate birth) {
        return new UpdateUserCommand(id, userName, personalName, email, phone, document, birth);
    }
}
