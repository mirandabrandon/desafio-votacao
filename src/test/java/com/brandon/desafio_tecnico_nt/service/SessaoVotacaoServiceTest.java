package com.brandon.desafio_tecnico_nt.service;

import com.brandon.desafio_tecnico_nt.model.Pauta;
import com.brandon.desafio_tecnico_nt.model.SessaoVotacao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class SessaoVotacaoServiceTest {

    @Autowired
    private SessaoVotacaoService sessaoVotacaoService;

    @Autowired
    private PautaService pautaService;

    @Test
    public void testAbrirSessao() {
        Pauta pauta = pautaService.createPauta(new Pauta("Pauta Teste Sessao"));
        SessaoVotacao sessao = sessaoVotacaoService.abrirSessao(pauta.getId(), 5);

        assertThat(sessao).isNotNull();
        assertThat(sessao.getDataInicio()).isBefore(LocalDateTime.now());
        assertThat(sessao.getDataFim()).isAfter(LocalDateTime.now());
    }
}

