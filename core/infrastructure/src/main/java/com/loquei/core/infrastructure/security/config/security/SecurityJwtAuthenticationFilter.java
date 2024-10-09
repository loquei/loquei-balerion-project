package com.loquei.core.infrastructure.security.config.security;

import com.loquei.core.domain.security.user.SecurityUser;
import com.loquei.core.domain.security.user.SecurityUserGateway;
import com.loquei.core.infrastructure.security.token.SecurityJwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Profile("production")
@RequiredArgsConstructor
public class SecurityJwtAuthenticationFilter extends SecurityAuthenticationFilter {

    private final SecurityJwtTokenService jwtService;
    private final SecurityUserGateway userGateway;

    @Override
    protected void doFilterInternal(
            final @NonNull HttpServletRequest request,
            final @NonNull HttpServletResponse response,
            final @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = extractAuth(request);

        if (Objects.isNull(authHeader) || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final var jwt = extractJwtToken(authHeader);
        final var email = jwtService.extractEmail(jwt);

        if (canAuthenticate(email)) {

            final var optionalUser = this.userGateway.findByEmail(email);

            if (isValidUserAuth(optionalUser, email, jwt)) {
                authenticate(email, request);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String extractAuth(final HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    private String extractJwtToken(final String auth) {
        return auth.substring(7);
    }

    private boolean canAuthenticate(final String email) {
        return Objects.nonNull(email)
                && Objects.isNull(SecurityContextHolder.getContext().getAuthentication());
    }

    private boolean isValidUserAuth(final Optional<SecurityUser> optionalUser, final String email, final String token) {
        return optionalUser.isPresent()
                && jwtService.validateToken(token)
                && email.equals(optionalUser.get().getEmail());
    }

    private void authenticate(final String email, final HttpServletRequest request) {
        final var authorities = List.of(new SimpleGrantedAuthority("USER"));

        final var authToken = new UsernamePasswordAuthenticationToken(email, null, authorities);

        authToken.setDetails(new WebAuthenticationDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}
