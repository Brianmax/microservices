package com.example.demo.service.impl;

import com.example.demo.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;

@Service
public class JwtServiceImpl implements JwtService {
    @Override
    public String generarToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userDetails.getAuthorities().toString());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS256, getSignKey())
                .compact();
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        Claims claims = extractClaims(token);
        String username = extractUsername(token);
        Date expiration = claims.getExpiration();
        return (username.equals(userDetails.getUsername()) && !expiration.before(new Date(System.currentTimeMillis())));
    }

    @Override
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }
    private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSignKey())
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignKey() {
        byte[] key = Base64.getDecoder().decode("85732b878c0f544da4a863804775ef3914e8ccb82b08820a278302c5b826e291");
        return Keys.hmacShaKeyFor(key);
    }
}
