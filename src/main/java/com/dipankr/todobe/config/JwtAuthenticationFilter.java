package com.dipankr.todobe.config;

import com.dipankr.todobe.repository.TokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;

    /**
     * Extracts and validates the JWT token
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain)
        throws ServletException, IOException {
        final String authenticationHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userEmail;
        if (null == authenticationHeader || !authenticationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwtToken = authenticationHeader.substring(7);
        userEmail = jwtService.extractUsername(jwtToken);
        //SecurityContextHolder.getContext().getAuthentication() == null --> indicates user not authenticated
        if (null != userEmail && null == SecurityContextHolder.getContext().getAuthentication()) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
            var isTokenValidInDb = tokenRepository.findByToken(jwtToken)
                .map(token -> !token.isExpired() && !token.isRevoked())
                .orElse(false);
            if (jwtService.isTokenValid(jwtToken, userDetails) && isTokenValidInDb) {
                //we create token and add request details to the token
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
                );
                authenticationToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
