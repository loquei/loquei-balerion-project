package com.loquei.core.infrastructure.security.user.persistence;

import com.loquei.core.domain.security.user.SecurityUser;
import com.loquei.core.domain.security.user.SecurityUserId;
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
@Entity(name = "SecurityUser")
@Table(name = "security_users")
public class SecurityUserJpaEntity {

    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    @Size(min = 32, max = 36)
    private String id;

    @Column(name = "username", unique = true, nullable = false)
    @Size(min = 3, max = 50)
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    @Size(min = 5, max = 255)
    private String email;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    public static SecurityUserJpaEntity from(final SecurityUser user) {
        return new SecurityUserJpaEntity(
                user.getId().getValue(), user.getUsername(), user.getEmail(), user.getCreatedAt(), user.getUpdatedAt());
    }

    public SecurityUser toAggregate() {
        return SecurityUser.with(
                SecurityUserId.from(getId()), getUsername(), getEmail(), getCreatedAt(), getUpdatedAt());
    }
}
