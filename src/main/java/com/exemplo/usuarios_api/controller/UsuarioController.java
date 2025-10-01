package com.exemplo.usuarios_api.controller;

import com.exemplo.usuarios_api.dto.UsuarioRequestDTO;
import com.exemplo.usuarios_api.dto.UsuarioResponseDTO;
import com.exemplo.usuarios_api.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "Operações de criação e consulta de usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Operation(summary = "Cria um novo usuário", description = "Cadastra um usuário com nome e e-mail")
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criar(@Valid @RequestBody UsuarioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @Operation(summary = "Lista usuários com paginação", description = "Lista paginada dos usuários da aplicação")
    @GetMapping
    public ResponseEntity<Page<UsuarioResponseDTO>> listar(
            @ParameterObject
            @PageableDefault(size = 10, sort = "id")
            Pageable pageable) {
        return ResponseEntity.ok(service.listar(pageable));
    }

    @Operation(summary = "Busca usuário por ID", description = "Retorna um usuário existente pelo seu identificador")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}
