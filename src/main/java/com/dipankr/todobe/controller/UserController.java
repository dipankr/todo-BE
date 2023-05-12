package com.dipankr.todobe.controller;

import com.dipankr.todobe.dto.AuthenticationRequest;
import com.dipankr.todobe.dto.AuthenticationResponse;
import com.dipankr.todobe.dto.RegisterRequest;
import com.dipankr.todobe.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
        AuthenticationResponse registerResponse = authenticationService.register(registerRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + registerResponse.getToken());
        return ResponseEntity.ok().headers(headers).body(registerResponse);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            AuthenticationResponse authenticationResponse = authenticationService.authenticate(request);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + authenticationResponse.getToken());
            return ResponseEntity.ok().headers(headers).body(authenticationResponse);
        } catch (Exception e) {
            var authFailedResponse = AuthenticationResponse.builder()
                .message("Wrong username or password")
                .build();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authFailedResponse);
        }
    }
}
