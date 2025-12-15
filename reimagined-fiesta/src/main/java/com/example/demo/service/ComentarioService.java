package com.example.demo.service;

import com.example.demo.dto.request.ComentarioCreateDto;

public interface ComentarioService {
    String createComentario(ComentarioCreateDto comentario);
}
