package com.brandon.desafio_tecnico_nt.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class VotoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRegistrarVoto() throws Exception {
        mockMvc.perform(post("/api/votos/registrar?pautaId=1&associadoId=123&voto=true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.voto").value(true));
    }

    @Test
    public void testGetResultadoVotacao() throws Exception {
        mockMvc.perform(get("/api/votos/resultado/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.votosSim").exists())
                .andExpect(jsonPath("$.votosNao").exists());
    }
}

