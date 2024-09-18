package com.loquei.core.application.user.create;

import java.time.LocalDate;

public record CreateUserCommand(
        String userName, String personalName, String email, String phone, String document, LocalDate birth) {

    public static CreateUserCommand with(
            final String userName,
            final String personalName,
            final String email,
            final String phone,
            final String document,
            final LocalDate birth) {
        return new CreateUserCommand(userName, personalName, email, phone, document, birth);
    }
}
