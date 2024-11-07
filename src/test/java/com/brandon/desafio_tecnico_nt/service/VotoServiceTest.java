package com.brandon.desafio_tecnico_nt.service;

import com.brandon.desafio_tecnico_nt.model.Pauta;
import com.brandon.desafio_tecnico_nt.model.SessaoVotacao;
import com.brandon.desafio_tecnico_nt.model.Voto;
import com.brandon.desafio_tecnico_nt.repository.SessaoVotacaoRepository;
import com.brandon.desafio_tecnico_nt.repository.VotoRepository;
import com.brandon.desafio_tecnico_nt.enuns.StatusSessao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class VotoServiceTest {

    @Mock
    private PautaService pautaService;

    @Mock
    private SessaoVotacaoService sessaoVotacaoService;

    @Mock
    private VotoRepository votoRepository;

    @Mock
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @InjectMocks
    private VotoService votoService;

    private Pauta pauta;
    private SessaoVotacao sessao;

    @BeforeEach
    public void setUp() {
        pauta = new Pauta("Pauta Voto Teste");
        pauta.setId(1L);

        sessao = new SessaoVotacao();
        sessao.setId(1L);
        sessao.setPauta(pauta);

        when(sessaoVotacaoRepository.findActiveSessionByPauta(pauta.getId(), StatusSessao.ATIVA))
                .thenReturn(Optional.of(sessao));
        when(votoRepository.save(any(Voto.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    public void testRegistrarVoto() {
        Long associadoId = 123L;
        Boolean votoValue = true;

        Voto voto = votoService.registrarVoto(pauta.getId(), associadoId, "201.946.040-80", votoValue);

        assertThat(voto).isNotNull();
        assertThat(voto.getAssociadoId()).isEqualTo(associadoId);
        assertThat(voto.getPauta()).isEqualTo(pauta);
        assertThat(voto.getVoto()).isTrue();
    }
}
