package com.loquei.core.infrastructure.security.facade;

import com.loquei.core.infrastructure.security.user.models.*;

public interface SecurityUserFacade {

    SecurityCreateUserResponse create(SecurityCreateUserRequest input);

    SecurityGetUserResponse getById(String id);

    SecurityGetUserResponse getByEmail(String email);

    SecurityUpdateUserResponse update(String id, SecurityUpdateUserRequest input);

    void deleteById(String id);
}
