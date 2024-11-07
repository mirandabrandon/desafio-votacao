package com.brandon.desafio_tecnico_nt.controller;

import com.brandon.desafio_tecnico_nt.model.Voto;
import com.brandon.desafio_tecnico_nt.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/votos")
public class VotoController {

    @Autowired
    private VotoService votoService;

    @PostMapping("/registrar")
    public ResponseEntity<Voto> registrarVoto(@RequestParam Long pautaId, @RequestParam Long associadoId, @RequestParam(required = false) String cpf, @RequestParam Boolean voto) {
        Voto novoVoto = votoService.registrarVoto(pautaId, associadoId, cpf, voto);
        return ResponseEntity.ok(novoVoto);
    }
}
