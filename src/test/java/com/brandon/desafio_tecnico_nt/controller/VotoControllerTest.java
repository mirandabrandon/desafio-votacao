package com.brandon.desafio_tecnico_nt.controller;

import com.brandon.desafio_tecnico_nt.enuns.StatusSessao;
import com.brandon.desafio_tecnico_nt.model.Pauta;
import com.brandon.desafio_tecnico_nt.model.SessaoVotacao;
import com.brandon.desafio_tecnico_nt.repository.PautaRepository;
import com.brandon.desafio_tecnico_nt.repository.SessaoVotacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class VotoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @BeforeEach
    public void setup() {
        Pauta pauta = new Pauta("Sample Pauta");
        pautaRepository.save(pauta);  // Saving a pauta

        SessaoVotacao sessaoVotacao = new SessaoVotacao();
        sessaoVotacao.setPauta(pauta);
        sessaoVotacao.setStatus(StatusSessao.ATIVA);
        sessaoVotacaoRepository.save(sessaoVotacao);
    }


    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void testRegistrarVoto() throws Exception {
        mockMvc.perform(post("/api/votos/registrar")
                        .param("pautaId", "1")
                        .param("associadoId", "123")
                        .param("voto", "true")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.voto").value(true));
    }
}
