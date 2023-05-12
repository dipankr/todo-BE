package com.dipankr.todobe.service;

import com.dipankr.todobe.dto.AuthenticationRequest;
import com.dipankr.todobe.dto.AuthenticationResponse;
import com.dipankr.todobe.dto.RegisterRequest;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest registerRequest);

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);

}
