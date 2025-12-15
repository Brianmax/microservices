package com.example.demo.service;

import com.example.demo.dto.request.ArticuloCreateDto;
import com.example.demo.dto.response.ArticuloResponseDto;
import com.example.demo.entity.ArticuloEntity;

import java.util.ArrayList;
import java.util.List;

public interface ArticuloService {
    ArticuloResponseDto create(ArticuloCreateDto articulo);
    List<ArticuloEntity> findAll();
    ArticuloResponseDto findById(Integer id);
    ArticuloEntity update(ArticuloEntity articulo, int id);
    void delete(int id);
    ArticuloEntity agregarCategoria(int articuloId, ArrayList<Integer> idCategorias);
}
