package com.exemplo.usuarios_api.service.exceptions;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(Long id) {
        super("Usuário: " + id + " não encontrado!");
    }
}
