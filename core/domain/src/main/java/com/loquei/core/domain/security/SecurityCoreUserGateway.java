package com.loquei.core.domain.security;

public interface SecurityCoreUserGateway {

    SecurityCoreUser create(SecurityCoreUser input);

    SecurityCoreUser getById(String id);

    SecurityCoreUser getByEmail(String email);

    SecurityCoreUser update(String id, SecurityCoreUser input);

    void deleteById(String id);
}
