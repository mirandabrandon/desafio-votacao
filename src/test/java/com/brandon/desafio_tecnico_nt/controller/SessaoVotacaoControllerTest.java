package com.brandon.desafio_tecnico_nt.controller;

import com.brandon.desafio_tecnico_nt.enuns.StatusSessao;
import com.brandon.desafio_tecnico_nt.model.Pauta;
import com.brandon.desafio_tecnico_nt.model.SessaoVotacao;
import com.brandon.desafio_tecnico_nt.repository.PautaRepository;
import com.brandon.desafio_tecnico_nt.repository.SessaoVotacaoRepository;
import com.brandon.desafio_tecnico_nt.repository.VotoRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class SessaoVotacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setup() {
        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY FALSE");

        votoRepository.deleteAll();
        sessaoVotacaoRepository.deleteAll();
        pautaRepository.deleteAll();

        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY TRUE");

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

    @AfterEach
    public void tearDown() {
        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY FALSE");

        votoRepository.deleteAll();
        sessaoVotacaoRepository.deleteAll();
        pautaRepository.deleteAll();

        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY TRUE");
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void testAbrirSessao() throws Exception {
        mockMvc.perform(post("/api/sessoes/abrir/{id}", 3)
                        .param("duracao", "5")
                        .param("status", StatusSessao.ATIVA.toString())
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNotEmpty())  // Verify ID of new SessaoVotacao is present
                .andExpect(jsonPath("$.duracao").value(5))
                .andExpect(jsonPath("$.status").value("ATIVA"));
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void testGetSessaoById() throws Exception {
        mockMvc.perform(get("/api/sessoes/{id}", 4)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(4));
    }
}
