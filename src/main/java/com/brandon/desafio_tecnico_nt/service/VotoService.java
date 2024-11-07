package com.brandon.desafio_tecnico_nt.service;

import com.brandon.desafio_tecnico_nt.enuns.StatusSessao;
import com.brandon.desafio_tecnico_nt.model.ResultadoVotacao;
import com.brandon.desafio_tecnico_nt.model.SessaoVotacao;
import com.brandon.desafio_tecnico_nt.model.Voto;
import com.brandon.desafio_tecnico_nt.repository.SessaoVotacaoRepository;
import com.brandon.desafio_tecnico_nt.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VotoService {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Autowired
    private CpfValidationService cpfValidationService;

    public Voto registrarVoto(Long pautaId, Long associadoId, String cpf, Boolean voto) {

        if (cpf != null && cpf.length() == 11) {
            boolean ableToVote = cpfValidationService.isAbleToVote(cpf);

            if (!ableToVote) {
                throw new RuntimeException("O associado não está habilitado para votar");
            }
        }

        SessaoVotacao sessaoVotacao = sessaoVotacaoRepository.findActiveSessionByPauta(pautaId, StatusSessao.ATIVA)
                .orElseThrow(() -> new RuntimeException("Nenhuma sessão de votação ativa para esta pauta"));

        Optional<Voto> votoExistente = votoRepository.findByAssociadoIdAndPautaId(associadoId, pautaId);
        if (votoExistente.isPresent()) {
            throw new RuntimeException("O associado já votou nesta pauta");
        }

        Voto novoVoto = new Voto(associadoId, sessaoVotacao.getPauta(), voto);
        return votoRepository.save(novoVoto);
    }

    public ResultadoVotacao calcularResultado(Long pautaId) {
        List<Voto> votos = votoRepository.findByPautaId(pautaId);
        long votosSim = votos.stream().filter(Voto::getVoto).count();
        long votosNao = votos.size() - votosSim;

        return new ResultadoVotacao(votosSim, votosNao);
    }
}

