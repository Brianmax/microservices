package com.example.demo.service;

import com.example.demo.dto.request.CreateUsuarioDto;
import com.example.demo.dto.response.ResponseUsuarioDto;

public interface UsuarioService {
    ResponseUsuarioDto crearUsuario(CreateUsuarioDto usuario);
}
