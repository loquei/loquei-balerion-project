package com.loquei.core.domain.security.user;

import java.util.Optional;

public interface SecurityUserGateway {

    SecurityUser create(SecurityUser user);

    SecurityUser update(SecurityUser user);

    Optional<SecurityUser> findById(SecurityUserId id);

    Optional<SecurityUser> findByEmail(String email);

    void delete(SecurityUserId id);
}
