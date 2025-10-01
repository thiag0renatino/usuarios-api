package com.exemplo.usuarios_api.service;

import com.exemplo.usuarios_api.dto.UsuarioRequestDTO;
import com.exemplo.usuarios_api.model.Usuario;
import com.exemplo.usuarios_api.repository.UsuarioRepository;
import com.exemplo.usuarios_api.service.exceptions.EmailAlreadyExistsException;
import com.exemplo.usuarios_api.service.exceptions.UsuarioNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    UsuarioRepository repository;

    @InjectMocks
    UsuarioService service;

    @Test
    @DisplayName("criar() salva e retorna DTO quando email é novo")
    void criarOk() {
        var req = new UsuarioRequestDTO("Thiago", "thiago@email.com");
        when(repository.existsByEmailIgnoreCase("thiago@email.com")).thenReturn(false);
        when(repository.save(any(Usuario.class)))
                .thenAnswer(inv -> {
                    Usuario u = inv.getArgument(0);
                    u.setId(1L);
                    return u;
                });

        var resp = service.criar(req);

        assertThat(resp.getId()).isEqualTo(1L);
        assertThat(resp.getNome()).isEqualTo("Thiago");
        assertThat(resp.getEmail()).isEqualTo("thiago@email.com");

        ArgumentCaptor<Usuario> captor = ArgumentCaptor.forClass(Usuario.class);
        verify(repository).save(captor.capture());
        assertThat(captor.getValue().getNome()).isEqualTo("Thiago");
        assertThat(captor.getValue().getEmail()).isEqualTo("thiago@email.com");
    }

    @Test
    @DisplayName("criar() lança EmailAlreadyExistsException quando email já existe")
    void criarDuplicado() {
        var req = new UsuarioRequestDTO("Thiago", "thiago@email.com");
        when(repository.existsByEmailIgnoreCase("thiago@email.com")).thenReturn(true);

        assertThatThrownBy(() -> service.criar(req))
                .isInstanceOf(EmailAlreadyExistsException.class);
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("buscarPorId() lança UsuarioNotFoundException quando não existe")
    void buscarPorIdNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.buscarPorId(99L))
                .isInstanceOf(UsuarioNotFoundException.class);
    }

    @Test
    @DisplayName("listar() retorna página de DTOs")
    void listarOk() {
        var pageReq = PageRequest.of(0, 10);
        var entities = List.of(
                new Usuario("A", "a@example.com"),
                new Usuario("B", "b@example.com")
        );
        when(repository.findAll(pageReq)).thenReturn(new PageImpl<>(entities, pageReq, entities.size()));

        var page = service.listar(pageReq);

        assertThat(page.getTotalElements()).isEqualTo(2);
        assertThat(page.getContent()).extracting("email")
                .containsExactlyInAnyOrder("a@example.com", "b@example.com");
    }
}
