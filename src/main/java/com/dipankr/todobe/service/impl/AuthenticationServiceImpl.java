package com.dipankr.todobe.service.impl;

import com.dipankr.todobe.config.JwtService;
import com.dipankr.todobe.dto.AuthenticationRequest;
import com.dipankr.todobe.dto.AuthenticationResponse;
import com.dipankr.todobe.dto.RegisterRequest;
import com.dipankr.todobe.entity.Role;
import com.dipankr.todobe.entity.User;
import com.dipankr.todobe.repository.TokenRepository;
import com.dipankr.todobe.repository.UserRepository;
import com.dipankr.todobe.service.AuthenticationService;
import com.dipankr.todobe.token.Token;
import com.dipankr.todobe.token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = User.builder()
            .name(registerRequest.getName())
            .email(registerRequest.getEmail())
            .password(passwordEncoder.encode(registerRequest.getPassword()))
            .role(Role.USER)
            .build();

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        revokeAllUserToken(user);
        saveUserToken(user, jwtToken);

        return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(),
                authenticationRequest.getPassword()
            )
        );

        var user = userRepository.findByEmail(authenticationRequest.getEmail())
            .orElseThrow(() -> new UsernameNotFoundException("user not found"));

        var jwtToken = jwtService.generateToken(user);

        revokeAllUserToken(user);
        saveUserToken(user, jwtToken);

        return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
    }

    private void revokeAllUserToken(User user) {
        var validUserTokens = tokenRepository.findAllValidTokensByUserId(user.getId());

        if (validUserTokens.isEmpty()) {
            return;
        }

        validUserTokens.forEach(token -> {
            token.setRevoked(true);
            token.setExpired(true);
        });

        tokenRepository.saveAll(validUserTokens);
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
            .user(user)
            .token(jwtToken)
            .tokenType(TokenType.BEARER)
            .expired(false)
            .revoked(false)
            .build();
        tokenRepository.save(token);
    }
}
