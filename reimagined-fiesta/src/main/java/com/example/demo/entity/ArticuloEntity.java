package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "articulos")
@Getter
@Setter
public class ArticuloEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "articulo_id")
    private int articuloId;
    private String titulo;
    @Column(columnDefinition = "TEXT")
    private String contenido;
    @Column(name = "fecha_creacion")
    private Date fechaCreacion = new Date();
    @Column(name = "fecha_actualizacion")
    private Date fechaActualizacion;
    @Column(name = "url_articulo")
    private String urlArticulo;

    @ManyToOne()
    @JoinColumn(name = "usuario_id_fk")
    private UsuarioEntity usuario;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "articulo_categoria",
            joinColumns = @JoinColumn(name = "articulo_id_fk"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id_fk")
    )
    private List<CategoriaEntity> categorias = new ArrayList<>();
}

// DTO: DATA TRANSFER OBJECT
