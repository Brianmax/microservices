package com.example.demo.dto.request;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateUsuarioDto {
    private String username;
    private String password;
    private String email;
    private Date fechaNacimiento;
    private String sexo;
    private String dni;
    private String role;
}
