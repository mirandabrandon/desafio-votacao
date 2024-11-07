package com.brandon.desafio_tecnico_nt.service;

import com.brandon.desafio_tecnico_nt.model.Pauta;
import com.brandon.desafio_tecnico_nt.model.SessaoVotacao;
import com.brandon.desafio_tecnico_nt.repository.PautaRepository;
import com.brandon.desafio_tecnico_nt.repository.SessaoVotacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import org.mockito.junit.jupiter.MockitoSettings;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class SessaoVotacaoServiceTest {

    @Mock
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private SessaoVotacaoService sessaoVotacaoService;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testAbrirSessao() {
        Pauta pauta = new Pauta("Pauta Teste Sessao");
        pauta.setId(3L);

        when(pautaRepository.findById(pauta.getId())).thenReturn(Optional.of(pauta));
        when(sessaoVotacaoRepository.save(org.mockito.ArgumentMatchers.any(SessaoVotacao.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        SessaoVotacao sessao = sessaoVotacaoService.abrirSessao(pauta.getId(), 5);

        assertThat(sessao).isNotNull();
        assertThat(sessao.getPauta()).isEqualTo(pauta);
        assertThat(sessao.getDataInicio()).isBefore(LocalDateTime.now());
        assertThat(sessao.getDataFim()).isEqualTo(sessao.getDataInicio().plusMinutes(5));
    }

    @Test
    public void testAbrirSessaoWithNonExistentPauta() {
        Long invalidPautaId = 999L;
        when(pautaRepository.findById(invalidPautaId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> sessaoVotacaoService.abrirSessao(invalidPautaId, 5),
                "Nenhuma pauta encontrada com o ID especificado");
    }
}
