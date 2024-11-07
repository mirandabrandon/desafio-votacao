package com.brandon.desafio_tecnico_nt.repository;

import com.brandon.desafio_tecnico_nt.enuns.StatusSessao;
import com.brandon.desafio_tecnico_nt.model.SessaoVotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long> {

    @Query("SELECT s FROM SessaoVotacao s WHERE s.pauta.id = :pautaId AND s.status = :status")
    Optional<SessaoVotacao> findActiveSessionByPauta(@Param("pautaId") Long pautaId, @Param("status") StatusSessao status);

    // busca todas as sessões que já expiraram e ainda estão ativas
    @Query("SELECT s FROM SessaoVotacao s WHERE s.dataFim < :dataAtual AND s.status = :status")
    List<SessaoVotacao> findByDataFimBeforeAndStatus(@Param("dataAtual") LocalDateTime dataAtual, @Param("status") StatusSessao status);


    Boolean existsByPautaId(Long pautaId);

    void deleteByPautaId(Long pautaId);
}
