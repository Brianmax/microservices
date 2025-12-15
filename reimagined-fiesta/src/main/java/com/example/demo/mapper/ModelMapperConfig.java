package com.example.demo.mapper;

import com.example.demo.dto.response.ArticuloResponseDto;
import com.example.demo.dto.response.CategoriaResponseArticuloDto;
import com.example.demo.dto.response.UsuarioResponseArticuloDto;
import com.example.demo.entity.ArticuloEntity;
import com.example.demo.entity.CategoriaEntity;
import com.example.demo.entity.UsuarioEntity;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // configurar

        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.createTypeMap(UsuarioEntity.class, UsuarioResponseArticuloDto.class)
                .addMapping(UsuarioEntity::getUsuarioId, UsuarioResponseArticuloDto::setIdUsuario)
                .addMapping(UsuarioEntity::getUsername, UsuarioResponseArticuloDto::setUsername)
                .addMapping(UsuarioEntity::getEmail, UsuarioResponseArticuloDto::setEmail);

        modelMapper.createTypeMap(CategoriaEntity.class, CategoriaResponseArticuloDto.class)
                .addMapping(CategoriaEntity::getCategoriaId, CategoriaResponseArticuloDto::setIdCategoria)
                .addMapping(CategoriaEntity::getNombreCategoria, CategoriaResponseArticuloDto::setNombreCategoria);



        modelMapper.createTypeMap(ArticuloEntity.class, ArticuloResponseDto.class)
                .addMapping(ArticuloEntity::getArticuloId, ArticuloResponseDto::setArticuloId)
                .addMapping(ArticuloEntity::getTitulo, ArticuloResponseDto::setTitulo)
                .addMapping(ArticuloEntity::getContenido, ArticuloResponseDto::setContenido)
                .addMapping(ArticuloEntity::getFechaCreacion, ArticuloResponseDto::setFechaCreacion)
                .addMapping(ArticuloEntity::getFechaActualizacion, ArticuloResponseDto::setFechaActualizacion)
                .addMapping(ArticuloEntity::getUrlArticulo, ArticuloResponseDto::setUrlArticulo);

        return modelMapper;
    }
}
