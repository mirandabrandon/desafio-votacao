package com.brandon.desafio_tecnico_nt.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PautaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreatePauta() throws Exception {
        mockMvc.perform(post("/api/pautas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Nova Pauta\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Nova Pauta"));
    }

    @Test
    public void testGetAllPautas() throws Exception {
        mockMvc.perform(get("/api/pautas"))
                .andExpect(status().isOk());
    }
}

