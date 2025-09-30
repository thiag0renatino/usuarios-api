package com.exemplo.usuarios_api.service;

import com.exemplo.usuarios_api.dto.UsuarioRequestDTO;
import com.exemplo.usuarios_api.dto.UsuarioResponseDTO;
import com.exemplo.usuarios_api.model.Usuario;
import com.exemplo.usuarios_api.repository.UsuarioRepository;
import com.exemplo.usuarios_api.service.exceptions.EmailAlreadyExistsException;
import com.exemplo.usuarios_api.service.exceptions.UsuarioNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioResponseDTO criar(UsuarioRequestDTO dto) {
        // Validação para caso email já exista
        if (repository.existsByEmailIgnoreCase(dto.getEmail())) {
            throw new EmailAlreadyExistsException(dto.getEmail());
        }

        Usuario usuario = repository.save(new Usuario(dto.getNome(), dto.getEmail()));
        return toResponseDTO(usuario);
    }

    public Page<UsuarioResponseDTO> listar(Pageable pageable) {
        return repository.findAll(pageable)
                .map(this::toResponseDTO);
    }

    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));
        return toResponseDTO(usuario);
    }

    private UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getDataCriacao(),
                usuario.getEmail()
        );
    }

}
