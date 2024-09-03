package com.loquei.core.infrastructure.user.persistence;

import com.loquei.core.domain.user.User;
import com.loquei.core.domain.user.UserId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.time.LocalDate;

@Entity(name = "User")
@Table(name = "users")
public class UserJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "personal_name", nullable = false)
    private String personalName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "document", nullable = false)
    private String document;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    public UserJpaEntity() {}

    public UserJpaEntity(
            final String id,
            final String username,
            final String personalName,
            final String email,
            final String phone,
            final String document,
            final LocalDate birth,
            final Instant createdAt,
            final Instant updatedAt) {
        this.id = id;
        this.username = username;
        this.personalName = personalName;
        this.email = email;
        this.phone = phone;
        this.document = document;
        this.birth = birth;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static UserJpaEntity from(final User user) {
        return new UserJpaEntity(
                user.getId().getValue(),
                user.getUserName(),
                user.getPersonalName(),
                user.getEmail(),
                user.getPhone(),
                user.getDocument(),
                user.getBirth(),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }

    public User toAggregate() {
        return User.with(
                UserId.from(getId()),
                getUsername(),
                getPersonalName(),
                getEmail(),
                getPhone(),
                getDocument(),
                getBirth(),
                getCreatedAt(),
                getUpdatedAt());
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPersonalName() {
        return personalName;
    }

    public void setPersonalName(final String personalName) {
        this.personalName = personalName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(final String document) {
        this.document = document;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(final LocalDate birth) {
        this.birth = birth;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(final Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
