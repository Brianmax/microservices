package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int usuarioId;
    private String nombres;
    private String apellidos;
    private String username;
    private String password;
    private String email;
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;
    private String sexo;
    private String dni;
    @Column(name = "comentarios_count")
    private int comentariosCount = 0;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;
}
