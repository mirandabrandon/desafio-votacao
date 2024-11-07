package com.brandon.desafio_tecnico_nt.service;

import com.brandon.desafio_tecnico_nt.model.Pauta;
import com.brandon.desafio_tecnico_nt.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PautaService {

    @Autowired
    private PautaRepository pautaRepository;

    public Pauta createPauta(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    public List<Pauta> getAllPautas() {
        return pautaRepository.findAll();
    }

    public Pauta getPautaById(Long id) {
        return pautaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pauta não encontrada"));
    }

    public Optional<Pauta> findById(Long invalidPautaId) {
        return pautaRepository.findById(invalidPautaId);
    }
}

