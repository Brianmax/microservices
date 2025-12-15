package com.example.demo.service.impl;

import com.example.demo.dto.request.ComentarioCreateDto;
import com.example.demo.entity.ArticuloEntity;
import com.example.demo.entity.ComentarioEntity;
import com.example.demo.entity.UsuarioEntity;
import com.example.demo.repository.ArticuloRepository;
import com.example.demo.repository.ComentarioRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.ComentarioService;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class ComentarioServiceImpl implements ComentarioService {
    private final ComentarioRepository comentarioRepository;
    private final ArticuloRepository articuloRepository;
    private final UsuarioRepository usuarioRepository;

    public ComentarioServiceImpl(ComentarioRepository comentarioRepository, ArticuloRepository articuloRepository, UsuarioRepository usuarioRepository) {
        this.comentarioRepository = comentarioRepository;
        this.articuloRepository = articuloRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public String createComentario(ComentarioCreateDto comentario) {
        Optional<ArticuloEntity> articuloOptional = articuloRepository.findById(comentario.getIdArticulo());
        Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findById(comentario.getIdUsuario());

        if (articuloOptional.isEmpty() || usuarioOptional.isEmpty()) {
            return null;
        }
        ArticuloEntity articulo = articuloOptional.get();
        UsuarioEntity usuario = usuarioOptional.get();
        ComentarioEntity comentarioEntity = new ComentarioEntity();
        comentarioEntity.setContenido(comentario.getContenido());
        comentarioEntity.setArticulo(articulo);
        comentarioEntity.setUsuario(usuario);
        int comentariosCount = usuario.getComentariosCount();
        usuario.setComentariosCount(usuario.getComentariosCount() + 1);
        usuarioRepository.save(usuario);
        comentarioRepository.save(comentarioEntity);
        return "Comentario creado correctamente";
    }
}
