package com.brandon.desafio_tecnico_nt.repository;

import com.brandon.desafio_tecnico_nt.model.SessaoVotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long> {

    @Query("SELECT s FROM SessaoVotacao s WHERE s.pauta.id = :pautaId AND :currentTime BETWEEN s.dataInicio AND s.dataFim")
    Optional<SessaoVotacao> findActiveSessionByPauta(@Param("pautaId") Long pautaId, @Param("currentTime") LocalDateTime currentTime);

    // busca todas as sessões que já expiraram e ainda estão ativas
    List<SessaoVotacao> findByDataFimBeforeAndAtivaTrue(LocalDateTime dataAtual);
}
