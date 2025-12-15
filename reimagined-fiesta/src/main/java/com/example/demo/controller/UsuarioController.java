package com.example.demo.controller;

import com.example.demo.dto.request.CreateUsuarioDto;
import com.example.demo.dto.response.ResponseUsuarioDto;
import com.example.demo.entity.UsuarioEntity;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;
@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioRepository usuarioRepository, UsuarioService usuarioService) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}")
    public UsuarioEntity getById(@PathVariable int id) {
        Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            UsuarioEntity usuariobd = usuarioOptional.get();
            return usuariobd;
        }
        return null;
    }
    @GetMapping("/find")
    public ArrayList<UsuarioEntity> findUsuario(
            @RequestParam(required = false) Date fechaNaciemiento,
            @RequestParam(required = false) String sexo) {
        return usuarioRepository.findByFechaNacimientoAndSexo(fechaNaciemiento, sexo);
    }
    @PostMapping("/save")
    public ResponseUsuarioDto saveUsuario(@RequestBody CreateUsuarioDto usuario) {
        return usuarioService.crearUsuario(usuario);
    }

    // hacer un find para articulo en el cual enviemos los siguientes parametros
    // fechaCreacion
    // fechaActualizacion
    // traer los articulos en los que la fecha de creacion sea mayor a una determinada fecha
    // o que su fecha de actualizacion sea menor a una determinada fecha
}
