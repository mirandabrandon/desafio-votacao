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
public class SessaoVotacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAbrirSessao() throws Exception {
        mockMvc.perform(post("/api/sessoes/abrir/1?duracao=5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.duracao").value(5));
    }
}

