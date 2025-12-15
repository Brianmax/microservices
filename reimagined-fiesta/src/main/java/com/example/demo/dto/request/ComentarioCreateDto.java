package com.example.demo.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioCreateDto {
    private String contenido;
    private int idUsuario;
    private int idArticulo;
}
