package com.example.demo.service.impl;

import com.example.demo.dto.request.ArticuloCreateDto;
import com.example.demo.dto.response.ArticuloResponseDto;
import com.example.demo.dto.response.CategoriaResponseArticuloDto;
import com.example.demo.dto.response.UsuarioResponseArticuloDto;
import com.example.demo.entity.ArticuloEntity;
import com.example.demo.entity.CategoriaEntity;
import com.example.demo.entity.UsuarioEntity;
import com.example.demo.repository.ArticuloRepository;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.ArticuloService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ArticuloServiceImpl implements ArticuloService {
    private final ArticuloRepository articuloRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;
    private final ModelMapper modelMapper;

    public ArticuloServiceImpl(ArticuloRepository articuloRepository, UsuarioRepository usuarioRepository, CategoriaRepository categoriaRepository, ModelMapper modelMapper) {
        this.articuloRepository = articuloRepository;
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public ArticuloResponseDto create(ArticuloCreateDto articulo) {
        int idUsuario = articulo.getUsuarioId();

        Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findById(idUsuario);
        if(usuarioOptional.isEmpty()) {
            return null;
        }
        UsuarioEntity usuarioEntity = usuarioOptional.get();
        ArticuloEntity articuloEntity = new ArticuloEntity();
        articuloEntity.setTitulo(articulo.getTitulo());
        articuloEntity.setContenido(articulo.getContenido());
        String uuidUrl = UUID.randomUUID().toString().replace("-", "");
        String url = "www." + usuarioEntity.getUsername() + ".articulos/" + uuidUrl;
        articuloEntity.setUrlArticulo(url);
        articuloEntity.setUsuario(usuarioEntity);
        articuloRepository.save(articuloEntity);
        ArticuloResponseDto articuloResponseDto = modelMapper.map(articuloEntity, ArticuloResponseDto.class);
        List<CategoriaEntity> categorias = articuloEntity.getCategorias();
        List<CategoriaResponseArticuloDto> categoriasDto = new ArrayList<>();
        for(CategoriaEntity categoria: categorias) {
            categoriasDto.add(modelMapper.map(categoria, CategoriaResponseArticuloDto.class));
        }
        articuloResponseDto.setUsuario(modelMapper.map(articuloEntity.getUsuario(), UsuarioResponseArticuloDto.class));
        articuloResponseDto.setCategorias(categoriasDto);
        return articuloResponseDto;
    }

    @Override
    public List<ArticuloEntity> findAll() {
        return List.of();
    }

    @Override
    public ArticuloResponseDto findById(Integer id) {
        ArticuloEntity articulo = articuloRepository.findById(id).orElse(null);
        if (articulo == null) {
            return null;
        }
        return modelMapper.map(articulo, ArticuloResponseDto.class);
    }

    @Override
    public ArticuloEntity update(ArticuloEntity articulo, int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public ArticuloEntity agregarCategoria(int articuloId, ArrayList<Integer> idCategorias) {
        Optional<ArticuloEntity> articuloOptional = articuloRepository.findById(articuloId);
        List<CategoriaEntity> categorias = categoriaRepository.findAllById(idCategorias);

        if(articuloOptional.isEmpty()) {
            log.debug("ARTICULO NO EXISTE PARA EL SIGUIENTE ID {}", articuloId);
            return null;
        }
        ArticuloEntity articuloEntity = articuloOptional.get();
        articuloEntity.setCategorias(categorias);
        articuloRepository.save(articuloEntity);

        return articuloEntity;
    }
}
