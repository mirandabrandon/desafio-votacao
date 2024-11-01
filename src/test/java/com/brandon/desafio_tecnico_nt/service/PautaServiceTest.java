package com.brandon.desafio_tecnico_nt.service;

import com.brandon.desafio_tecnico_nt.model.Pauta;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PautaServiceTest {

    @Autowired
    private PautaService pautaService;

    @Test
    public void testCreatePauta() {
        Pauta pauta = new Pauta("Pauta Teste");
        Pauta novaPauta = pautaService.createPauta(pauta);
        assertThat(novaPauta).isNotNull();
        assertThat(novaPauta.getNome()).isEqualTo("Pauta Teste");
    }

    @Test
    public void testGetAllPautas() {
        List<Pauta> pautas = pautaService.getAllPautas();
        assertThat(pautas).isNotEmpty();
    }
}

