package com.exemplo.usuarios_api.repository;

import com.exemplo.usuarios_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
