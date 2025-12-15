package com.example.demo.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticuloResponseDto {
    private int articuloId;
    private String titulo;
    private String contenido;
    private Date fechaCreacion;
    private Date fechaActualizacion;
    private String urlArticulo;
    private UsuarioResponseArticuloDto usuario;
    private List<CategoriaResponseArticuloDto> categorias;
}
