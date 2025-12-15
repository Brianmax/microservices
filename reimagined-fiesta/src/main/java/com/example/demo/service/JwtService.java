package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generarToken(UserDetails userDetails);
    boolean validateToken(String token, UserDetails userDetails);
    String extractUsername(String token);
}
