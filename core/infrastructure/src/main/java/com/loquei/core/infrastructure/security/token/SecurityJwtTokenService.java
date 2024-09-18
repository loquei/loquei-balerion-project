package com.loquei.core.infrastructure.security.token;

import com.loquei.core.domain.security.token.SecurityTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;

@Component
public class SecurityJwtTokenService implements SecurityTokenService {

    private static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();

    @Override
    public String generateToken(final String email, Long duration) {
        final Date now = new Date();
        final Date expiryDate = new Date(now.getTime() + duration);

        return Jwts.builder()
                .subject(email)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(SECRET_KEY)
                .compact();
    }

    @Override
    public String extractEmail(final String token) {
        return parseToken(token).getPayload().getSubject();
    }

    @Override
    public boolean validateToken(final String token) {
        try {
            final Jws<Claims> claims = parseToken(token);
            return !claims.getPayload().getExpiration().before(new Date());
        } catch (final Exception e) {
            return false;
        }
    }

    private Jws<Claims> parseToken(final String token) {
        return Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token);
    }
}
