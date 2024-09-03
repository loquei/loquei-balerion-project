package com.loquei.core.domain.user;

import com.loquei.common.validation.Error;
import com.loquei.common.validation.ValidationHandler;
import com.loquei.common.validation.Validator;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

public class UserValidator extends Validator {

    private static final Integer USERNAME_MAX_LENGTH = 50;
    private static final Integer USERNAME_MIN_LENGTH = 3;
    private static final Integer PERSONAL_NAME_MAX_LENGTH = 255;
    private static final Integer PERSONAL_NAME_MIN_LENGTH = 5;
    private static final Integer EMAIL_MAX_LENGTH = 255;
    private static final Integer EMAIL_MIN_LENGTH = 5;
    private static final Integer PHONE_MAX_LENGTH = 11;
    private static final Integer PHONE_MIN_LENGTH = 8;
    private static final Integer DOCUMENT_MAX_LENGTH = 14;
    private static final Integer DOCUMENT_MIN_LENGTH = 8;

    private final User user;

    public UserValidator(final User user, final ValidationHandler aHandler) {
        super(aHandler);
        this.user = user;
    }

    @Override
    public void validate() {
        this.checkUsernameConstraints();
        this.checkPersonalNameConstraints();
        this.checkEmailConstraints();
        this.checkPhoneConstraints();
        this.checkDocumentConstraints();
        this.checkBirthConstraints();
    }

    private void checkUsernameConstraints() {
        final var username = this.user.getUserName();
        if (Objects.isNull(username)) {
            this.validationHandler().append(new Error("'username' should not be null"));
            return;
        }
        if (username.isBlank() || username.isEmpty()) {
            this.validationHandler().append(new Error("'username' should not be empty"));
            return;
        }
        final int length = username.trim().length();
        if (length > USERNAME_MAX_LENGTH || length < USERNAME_MIN_LENGTH) {
            this.validationHandler().append(new Error("'username' must be between 3 and 50 characters"));
        }
    }

    private void checkPersonalNameConstraints() {
        final var personalName = this.user.getPersonalName();
        if (Objects.isNull(personalName)) {
            this.validationHandler().append(new Error("'personal name' should not be null"));
            return;
        }
        if (personalName.isBlank() || personalName.isEmpty()) {
            this.validationHandler().append(new Error("'personal name' should not be empty"));
            return;
        }
        final int length = personalName.trim().length();
        if (length > PERSONAL_NAME_MAX_LENGTH || length < PERSONAL_NAME_MIN_LENGTH) {
            this.validationHandler().append(new Error("'personal name' must be between 5 and 255 characters"));
        }
    }

    private void checkEmailConstraints() {
        final var email = this.user.getEmail();
        if (Objects.isNull(email)) {
            this.validationHandler().append(new Error("'email' should not be null"));
            return;
        }
        if (email.isBlank() || email.isEmpty()) {
            this.validationHandler().append(new Error("'email' should not be empty"));
            return;
        }
        final int length = email.trim().length();
        if (length > EMAIL_MAX_LENGTH || length < EMAIL_MIN_LENGTH) {
            this.validationHandler().append(new Error("'email' must be between 5 and 255 characters"));
            return;
        }
        if (!isValidEmailFormat(email)) {
            this.validationHandler().append(new Error("'email' must use 'example@email.com' format"));
        }
    }

    private static boolean isValidEmailFormat(final String email) {
        final var regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        final var pattern = Pattern.compile(regex);

        final var matcher = pattern.matcher(email);

        return matcher.matches();
    }

    private void checkPhoneConstraints() {
        final var phone = this.user.getPhone();
        if (Objects.isNull(phone)) {
            this.validationHandler().append(new Error("'phone' should not be null"));
            return;
        }
        if (phone.isBlank() || phone.isEmpty()) {
            this.validationHandler().append(new Error("'phone' should not be empty"));
            return;
        }
        final int length = phone.trim().length();
        if (length > PHONE_MAX_LENGTH || length < PHONE_MIN_LENGTH) {
            this.validationHandler().append(new Error("'phone' must be between 8 and 11 characters"));
        }
    }

    private void checkDocumentConstraints() {
        final var document = this.user.getDocument();
        if (Objects.isNull(document)) {
            this.validationHandler().append(new Error("'document' should not be null"));
            return;
        }
        if (document.isBlank() || document.isEmpty()) {
            this.validationHandler().append(new Error("'document' should not be empty"));
            return;
        }
        final int length = document.trim().length();
        if (length > DOCUMENT_MAX_LENGTH || length < DOCUMENT_MIN_LENGTH) {
            this.validationHandler().append(new Error("'document' must be between 8 and 14 characters"));
        }
    }

    private void checkBirthConstraints() {
        final var birth = this.user.getBirth();
        if (Objects.isNull(birth)) {
            this.validationHandler().append(new Error("'birth' should not be null"));
            return;
        }
        if (!isAdult(birth)) {
            this.validationHandler().append(new Error("'birth' user should be older than 18"));
            return;
        }
        if (!isNotTooOld(birth)) {
            this.validationHandler().append(new Error("'birth' user should be younger than 120"));
        }
    }

    private static boolean isAdult(final LocalDate dateOfBirth) {
        final var currentDate = LocalDate.now();
        final var age = currentDate.getYear() - dateOfBirth.getYear();
        final var minAge = 18;

        return age >= minAge;
    }

    private static boolean isNotTooOld(final LocalDate dateOfBirth) {
        final var currentDate = LocalDate.now();
        final var age = currentDate.getYear() - dateOfBirth.getYear();
        final var maxAge = 120;

        return age <= maxAge;
    }
}
