package com.loquei.core.domain.security.token;

public interface SecurityTokenService {
    String generateToken(String email, Long duration);

    boolean validateToken(String token);

    String extractEmail(String token);
}
