package com.brandon.desafio_tecnico_nt.service;

import com.brandon.desafio_tecnico_nt.model.Pauta;
import com.brandon.desafio_tecnico_nt.model.ResultadoVotacao;
import com.brandon.desafio_tecnico_nt.model.SessaoVotacao;
import com.brandon.desafio_tecnico_nt.repository.PautaRepository;
import com.brandon.desafio_tecnico_nt.repository.SessaoVotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SessaoVotacaoService {

    @Autowired
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private VotoService votoService;

    @Autowired
    private MessageQueueService messageQueueService;

    public SessaoVotacao abrirSessao(Long pautaId, Integer duracao) {
        Pauta pauta = pautaRepository.findById(pautaId)
                .orElseThrow(() -> new RuntimeException("Pauta não encontrada"));

        Optional<SessaoVotacao> sessaoAtiva = sessaoVotacaoRepository.findActiveSessionByPauta(pautaId, LocalDateTime.now());
        if (sessaoAtiva.isPresent()) {
            throw new RuntimeException("Já existe uma sessão de votação aberta para esta pauta");
        }

        SessaoVotacao sessaoVotacao = new SessaoVotacao(pauta, duracao != null ? duracao : 1);
        return sessaoVotacaoRepository.save(sessaoVotacao);
    }

    public SessaoVotacao getSessaoById(Long id) {
        return sessaoVotacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sessão não encontrada"));
    }

    public void fecharSessao(Long pautaId) {
        SessaoVotacao sessaoVotacao = sessaoVotacaoRepository.findById(pautaId)
                .orElseThrow(() -> new RuntimeException("Sessão não encontrada"));

        // calcula o resultado da votação
        ResultadoVotacao resultado = votoService.calcularResultado(pautaId);
        String mensagemResultado = String.format("Resultado da votação para pauta %d: %d votos Sim, %d votos Não",
                pautaId, resultado.getVotosSim(), resultado.getVotosNao());

        // envia o resultado para a fila SQS
        messageQueueService.sendVotingResult(mensagemResultado);

        // marca a sessão como encerrada
        sessaoVotacao.setAtiva(false);
        sessaoVotacaoRepository.save(sessaoVotacao);
    }

    @Scheduled(fixedRate = 60000) // executa a cada 60 segundos
    public void verificarSessoesExpiradas() {
        LocalDateTime agora = LocalDateTime.now();
        List<SessaoVotacao> sessoesAbertas = sessaoVotacaoRepository.findByDataFimBeforeAndAtivaTrue(agora);

        for (SessaoVotacao sessao : sessoesAbertas) {
            fecharSessao(sessao.getPauta().getId());
        }
    }
}

