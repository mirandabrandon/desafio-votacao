package com.brandon.desafio_tecnico_nt.service;

import com.brandon.desafio_tecnico_nt.model.Pauta;
import com.brandon.desafio_tecnico_nt.repository.PautaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PautaServiceTest {

    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private PautaService pautaService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePauta() {
        Pauta pauta = new Pauta("Pauta Teste");
        when(pautaRepository.save(pauta)).thenReturn(pauta); // Mock repository save behavior

        Pauta novaPauta = pautaService.createPauta(pauta);

        assertThat(novaPauta).isNotNull();
        assertThat(novaPauta.getNome()).isEqualTo("Pauta Teste");
    }

    @Test
    public void testGetAllPautas() {
        Pauta pauta = new Pauta("Pauta Teste");
        when(pautaRepository.findAll()).thenReturn(Collections.singletonList(pauta)); // Mock repository findAll behavior

        List<Pauta> pautas = pautaService.getAllPautas();

        assertThat(pautas).isNotEmpty();
        assertThat(pautas.get(0).getNome()).isEqualTo("Pauta Teste");
    }
}
