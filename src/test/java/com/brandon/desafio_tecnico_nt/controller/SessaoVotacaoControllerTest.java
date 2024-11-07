package com.brandon.desafio_tecnico_nt.controller;

import com.brandon.desafio_tecnico_nt.enuns.StatusSessao;
import com.brandon.desafio_tecnico_nt.model.Pauta;
import com.brandon.desafio_tecnico_nt.model.SessaoVotacao;
import com.brandon.desafio_tecnico_nt.repository.PautaRepository;
import com.brandon.desafio_tecnico_nt.repository.SessaoVotacaoRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SessaoVotacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    public void setup() {
        entityManager.createQuery("DELETE FROM SessaoVotacao").executeUpdate();
        entityManager.createQuery("DELETE FROM Pauta").executeUpdate();

        entityManager.flush();
        entityManager.clear();

        Pauta pauta = new Pauta();
        pauta.setNome("Test Pauta");
        pautaRepository.save(pauta);

        SessaoVotacao sessao = new SessaoVotacao();
        sessao.setPauta(pauta);
        sessao.setDuracao(5);
        sessao.setDataInicio(LocalDateTime.now());
        sessao.setStatus(StatusSessao.ATIVA);
        sessaoVotacaoRepository.save(sessao);
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void testAbrirSessao() throws Exception {
        mockMvc.perform(post("/api/sessoes/abrir/{id}", 1)
                        .param("duracao", "5")
                        .param("status", StatusSessao.ATIVA.toString())
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(2))  // ID of new SessaoVotacao
                .andExpect(jsonPath("$.duracao").value(5))
                .andExpect(jsonPath("$.status").value("ATIVA"));
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void testGetSessaoById() throws Exception {
        mockMvc.perform(get("/api/sessoes/{id}", 3)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(3));
    }
}



