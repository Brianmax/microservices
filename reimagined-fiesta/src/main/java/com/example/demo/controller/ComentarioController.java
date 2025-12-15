package com.example.demo.controller;

import com.example.demo.dto.request.ComentarioCreateDto;
import com.example.demo.service.ComentarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comentario")
public class ComentarioController {
    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @PostMapping("/save")
    public String createComentario(@RequestBody ComentarioCreateDto comentario){
        return comentarioService.createComentario(comentario);
    }
}
