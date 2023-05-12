package com.dipankr.todobe.config;

import com.dipankr.todobe.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final TokenRepository tokenRepository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String authenticationHeader = request.getHeader("Authorization");
        final String jwtToken;
        if (null == authenticationHeader || !authenticationHeader.startsWith("Bearer ")) {
            return;
        }
        jwtToken = authenticationHeader.substring(7);

        var storedToken = tokenRepository.findByToken(jwtToken).orElse(null);

        if (null != storedToken) {
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            tokenRepository.save(storedToken);
        }
    }
}
