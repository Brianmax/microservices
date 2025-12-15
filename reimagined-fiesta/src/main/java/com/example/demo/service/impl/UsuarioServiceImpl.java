package com.example.demo.service.impl;

import com.example.demo.dto.request.CreateUsuarioDto;
import com.example.demo.dto.response.ReniecResponse;
import com.example.demo.dto.response.ResponseUsuarioDto;
import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UsuarioEntity;
import com.example.demo.feignClient.ReniecClient;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final ReniecClient reniecClient;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    @Value("${api.token}")
    private String token;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, ReniecClient reniecClient, ModelMapper modelMapper, RoleRepository roleRepository) {
        this.usuarioRepository = usuarioRepository;
        this.reniecClient = reniecClient;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public ResponseUsuarioDto crearUsuario(CreateUsuarioDto usuario) {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setUsername(usuario.getUsername());
        usuarioEntity.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        usuarioEntity.setEmail(usuario.getEmail());
        usuarioEntity.setFechaNacimiento(usuario.getFechaNacimiento());
        usuarioEntity.setSexo(usuario.getSexo());
        usuarioEntity.setDni(usuario.getDni());

        Optional<RoleEntity> roleOptional = roleRepository.findByRole(usuario.getRole());
        if (roleOptional.isEmpty()) {
            return null;
        }
        ReniecResponse response = reniecClient.getPersonaInfo(usuario.getDni(), token);
        if (response == null) {
            return null;
        }
        usuarioEntity.setNombres(response.getFirstName());
        usuarioEntity.setApellidos(response.getFirstLastName() + " " + response.getSecondLastName());
        usuarioEntity.setRole(roleOptional.get());
        usuarioRepository.save(usuarioEntity);
        return new ResponseUsuarioDto(
                usuarioEntity.getNombres(),
                usuarioEntity.getApellidos(),
                usuarioEntity.getUsername(),
                usuarioEntity.getEmail(),
                usuarioEntity.getFechaNacimiento(),
                usuarioEntity.getSexo(),
                usuarioEntity.getDni(),
                usuarioEntity.getComentariosCount()
        );
    }
}
