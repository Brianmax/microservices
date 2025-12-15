package com.example.demo.service;

import com.example.demo.dto.request.CreateUsuarioDto;
import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.response.ResponseUsuarioDto;

public interface AuthService {
    String login(LoginRequest loginRequest);
    ResponseUsuarioDto register(CreateUsuarioDto createUsuarioDto);
}
