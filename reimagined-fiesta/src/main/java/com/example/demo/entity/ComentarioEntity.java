package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "comentarios")
public class ComentarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comenatario_id")
    private int comentarioId;

    @Column(length = 512)
    private String contenido;

    private Date fechaCreacion = new Date();
    private Date fechaActualizacion;

    @ManyToOne
    @JoinColumn(name = "articulo_id_fk")
    private ArticuloEntity articulo;

    @ManyToOne
    @JoinColumn(name = "usuario_id_fk")
    private UsuarioEntity usuario;

}
