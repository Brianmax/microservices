package com.example.demo.controller;

import com.example.demo.dto.request.ArticuloCreateDto;
import com.example.demo.dto.response.ArticuloResponseDto;
import com.example.demo.entity.ArticuloEntity;
import com.example.demo.service.ArticuloService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/articulo")
public class ArticuloController {
    private final ArticuloService articuloService;

    public ArticuloController(ArticuloService articuloService) {
        this.articuloService = articuloService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<ArticuloResponseDto> save(@Valid @RequestBody ArticuloCreateDto articulo) {
        ArticuloResponseDto art = articuloService.create(articulo);
        return ResponseEntity.status(HttpStatus.CREATED).body(art);
    }
    // cambiar la respuesta de tipo ArticuloEntity a ArticuloResponseDto

    @PostMapping("/categoria")
    public ArticuloEntity agregarCategorias(
            @RequestParam int articuloId,
            @RequestParam ArrayList<Integer> idCategorias){
        return articuloService.agregarCategoria(articuloId, idCategorias);
    }
    @GetMapping("/{id}")
    public ArticuloResponseDto findById(@PathVariable int id) {
        return articuloService.findById(id);
    }
}
