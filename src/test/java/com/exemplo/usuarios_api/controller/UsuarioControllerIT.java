package com.exemplo.usuarios_api.controller;

import com.exemplo.usuarios_api.dto.UsuarioRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UsuarioControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void criarUsuarioComSucesso() throws Exception {
        UsuarioRequestDTO dto = new UsuarioRequestDTO("Thiago Teste", "thiago.teste@email.com");

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").value("Thiago Teste"))
                .andExpect(jsonPath("$.email").value("thiago.teste@email.com"));
    }

    @Test
    void listarUsuariosPaginado() throws Exception {
        mockMvc.perform(get("/usuarios")
                        .param("page", "0")
                        .param("size", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    void buscarPorIdNotFound() throws Exception {
        mockMvc.perform(get("/usuarios/{id}", 99999L))
                .andExpect(status().isNotFound());
    }
}