package com.loquei.core.domain.security.auth;

import java.util.Optional;

public interface SecurityAuthGateway {
    SecurityAuth create(SecurityAuth auth);

    Optional<SecurityAuth> findMostRecentByEmail(String email);

    void delete(SecurityAuthId authId);
}
