package com.brandon.desafio_tecnico_nt.controller;

import com.brandon.desafio_tecnico_nt.model.SessaoVotacao;
import com.brandon.desafio_tecnico_nt.service.SessaoVotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sessoes")
public class SessaoVotacaoController {

    @Autowired
    private SessaoVotacaoService sessaoVotacaoService;

    @PostMapping("/abrir/{pautaId}")
    public ResponseEntity<SessaoVotacao> abrirSessao(@PathVariable Long pautaId, @RequestParam(required = false) Integer duracao) {
        SessaoVotacao sessaoVotacao = sessaoVotacaoService.abrirSessao(pautaId, duracao);
        return ResponseEntity.ok(sessaoVotacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessaoVotacao> getSessaoById(@PathVariable Long id) {
        return ResponseEntity.ok(sessaoVotacaoService.getSessaoById(id));
    }
}

