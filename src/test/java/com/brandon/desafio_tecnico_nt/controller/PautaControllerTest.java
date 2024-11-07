package com.brandon.desafio_tecnico_nt.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class PautaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreatePauta() throws Exception {
        mockMvc.perform(post("/api/pautas")
                        .with(SecurityMockMvcRequestPostProcessors.user("testUser").roles("USER")) // Mock user
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Nova Pauta\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Nova Pauta")); // Check response content
    }

    @Test
    public void testCreatePautaWithInvalidData() throws Exception {
        mockMvc.perform(post("/api/pautas")
                        .with(SecurityMockMvcRequestPostProcessors.user("testUser").roles("USER"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")) // Missing "nome" field
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetAllPautas() throws Exception {
        mockMvc.perform(get("/api/pautas")
                        .with(SecurityMockMvcRequestPostProcessors.user("testUser").roles("USER"))) // Mock user
                .andExpect(status().isOk());
    }
}

