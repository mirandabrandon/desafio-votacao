package com.brandon.desafio_tecnico_nt.service;

import com.brandon.desafio_tecnico_nt.model.Pauta;
import com.brandon.desafio_tecnico_nt.model.SessaoVotacao;
import com.brandon.desafio_tecnico_nt.model.Voto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class VotoServiceTest {

    @Autowired
    private VotoService votoService;

    @Autowired
    private PautaService pautaService;

    @Autowired
    private SessaoVotacaoService sessaoVotacaoService;

    @Test
    public void testRegistrarVoto() {
        Pauta pauta = pautaService.createPauta(new Pauta("Pauta Voto Teste"));
        SessaoVotacao sessao = sessaoVotacaoService.abrirSessao(pauta.getId(), 5);

        Voto voto = votoService.registrarVoto(pauta.getId(), 123L, "201.946.040-80", true);
        assertThat(voto).isNotNull();
        assertThat(voto.getVoto()).isTrue();
    }
}

