package com.loquei.core.domain.user;

import com.loquei.common.AggregateRoot;
import com.loquei.common.utils.InstantUtils;
import com.loquei.common.validation.ValidationHandler;
import java.time.Instant;
import java.time.LocalDate;

public class User extends AggregateRoot<UserId> {

    private String userName;
    private String personalName;
    private String email;
    private String phone;
    private String document;
    private LocalDate birth;
    private final Instant createdAt;
    private Instant updatedAt;

    private User(
            final UserId anId,
            final String userName,
            final String personalName,
            final String email,
            final String phone,
            final String document,
            final LocalDate birth,
            final Instant createdAt,
            final Instant updatedAt) {
        super(anId);
        this.userName = userName;
        this.personalName = personalName;
        this.email = email;
        this.phone = phone;
        this.document = document;
        this.birth = birth;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static User newUser(
            final String userName,
            final String personalName,
            final String email,
            final String phone,
            final String document,
            final LocalDate birth) {
        final var id = UserId.unique();
        final var now = InstantUtils.now();
        return new User(id, userName, personalName, email, phone, document, birth, now, now);
    }

    public static User with(
            final UserId anId,
            final String userName,
            final String personalName,
            final String email,
            final String phone,
            final String document,
            final LocalDate birth,
            final Instant createdAt,
            final Instant updatedAt) {
        return new User(anId, userName, personalName, email, phone, document, birth, createdAt, updatedAt);
    }

    public static User with(final User user) {
        return new User(
                user.getId(),
                user.getUserName(),
                user.getPersonalName(),
                user.getEmail(),
                user.getPhone(),
                user.getDocument(),
                user.getBirth(),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }

    @Override
    public void validate(final ValidationHandler aHandler) {
        new UserValidator(this, aHandler).validate();
    }

    public User update(
            final String userName,
            final String personalName,
            final String email,
            final String phone,
            final String document,
            final LocalDate birth) {
        this.userName = userName;
        this.personalName = personalName;
        this.email = email;
        this.phone = phone;
        this.document = document;
        this.birth = birth;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public String getPersonalName() {
        return personalName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getDocument() {
        return document;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
