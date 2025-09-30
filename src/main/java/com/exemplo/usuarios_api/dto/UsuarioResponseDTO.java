package com.exemplo.usuarios_api.dto;

import java.time.Instant;

public class UsuarioResponseDTO {

    private Long id;

    private String nome;

    private String email;

    private Instant dataCriacao;

    public UsuarioResponseDTO() {
    }

    public UsuarioResponseDTO(Long id, String nome, Instant dataCriacao, String email) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Instant dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
