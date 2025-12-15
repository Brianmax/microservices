package com.example.demo.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ResponseUsuarioDto {
    private String nombres;
    private String apellidos;
    private String username;
    private String email;
    private Date fechaNacimiento;
    private String sexo;
    private String dni;
    private int comentariosCount = 0;
}
