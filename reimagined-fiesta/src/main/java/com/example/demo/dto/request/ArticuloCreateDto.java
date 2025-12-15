package com.example.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticuloCreateDto {
    @NotBlank(message = "El titulo es requerido")
    @Size(min = 5, max = 200, message = "El titulo debe de tener entre 5 y 200 caracteres")
    private String titulo;

    @NotBlank(message = "El contenido es obligatorio")
    @Size(min = 10, message = "El contenido debe de tener mas de 10 caracteres")
    private String contenido;
    @Positive(message = "El ID del usuario debe de ser un numero positivo")
    private int usuarioId;
}
