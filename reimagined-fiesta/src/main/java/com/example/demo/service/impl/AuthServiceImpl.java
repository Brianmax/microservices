package com.example.demo.service.impl;

import com.example.demo.dto.request.CreateUsuarioDto;
import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.response.ResponseUsuarioDto;
import com.example.demo.security.UserDetailsServiceImpl;
import com.example.demo.service.AuthService;
import com.example.demo.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;

    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtService jwtService, UserDetailsServiceImpl userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public String login(LoginRequest loginRequest) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));
        if (auth.isAuthenticated()) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
            return jwtService.generarToken(userDetails);
        }
        return null;
    }

    @Override
    public ResponseUsuarioDto register(CreateUsuarioDto createUsuarioDto) {
        return null;
    }
}
