package com.loquei.core.infrastructure.security.auth.persistence;

import com.loquei.core.domain.security.auth.SecurityAuth;
import com.loquei.core.domain.security.auth.SecurityAuthCode;
import com.loquei.core.domain.security.auth.SecurityAuthId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "SecurityAuthentication")
@Table(name = "security_authentications")
public class SecurityAuthJpaEntity {

    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    @Size(min = 32, max = 36)
    private String id;

    @Column(name = "email", unique = true, nullable = false, updatable = false)
    @Size(min = 5, max = 255)
    private String email;

    @Column(name = "auth_code", nullable = false, updatable = false)
    @Size(min = 6, max = 6)
    private String authCode;

    @Column(name = "auth_token", nullable = false, updatable = false)
    @Size(min = 1, max = 255)
    private String authToken;

    @Column(name = "expires_at", nullable = false, updatable = false, columnDefinition = "DATETIME(6)")
    private Instant expiresAt;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    public static SecurityAuthJpaEntity from(final SecurityAuth auth) {
        return new SecurityAuthJpaEntity(
                auth.getId().getValue(),
                auth.getEmail(),
                auth.getAuthCode().getValue(),
                auth.getAuthToken(),
                auth.getExpiresAt(),
                auth.getCreatedAt());
    }

    public SecurityAuth toAggregate() {
        return SecurityAuth.with(
                SecurityAuthId.from(this.getId()),
                this.getEmail(),
                SecurityAuthCode.from(this.getAuthCode()),
                this.getAuthToken(),
                this.getExpiresAt(),
                this.getCreatedAt());
    }
}
