package com.brandon.desafio_tecnico_nt.controller;

import com.brandon.desafio_tecnico_nt.model.Pauta;
import com.brandon.desafio_tecnico_nt.model.ResultadoVotacao;
import com.brandon.desafio_tecnico_nt.service.PautaService;
import com.brandon.desafio_tecnico_nt.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pautas")
public class PautaController {

    @Autowired
    private PautaService pautaService;

    @Autowired
    private VotoService votoService;

    @PostMapping
    public ResponseEntity<Pauta> createPauta(@RequestBody Pauta pauta) {
        Pauta novaPauta = pautaService.createPauta(pauta);
        return ResponseEntity.ok(novaPauta);
    }

    @GetMapping
    public ResponseEntity<List<Pauta>> getAllPautas() {
        return ResponseEntity.ok(pautaService.getAllPautas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pauta> getPautaById(@PathVariable Long id) {
        return ResponseEntity.ok(pautaService.getPautaById(id));
    }

    @GetMapping("/{id}/resultado")
    public ResponseEntity<ResultadoVotacao> getResultadoVotacao(@PathVariable Long id) {
        ResultadoVotacao resultado = votoService.calcularResultado(id);
        return ResponseEntity.ok(resultado);
    }

}

