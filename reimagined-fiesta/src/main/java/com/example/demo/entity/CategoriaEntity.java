package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categorias")
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_id")
    private int categoriaId;
    private String descripcion;
    private double popularidad;
    @Column(name = "nombre_categoria")
    private String nombreCategoria;

    @ManyToMany(mappedBy = "categorias")
    private List<ArticuloEntity> articulos;
}
