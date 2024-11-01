package com.brandon.desafio_tecnico_nt.repository;

import com.brandon.desafio_tecnico_nt.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VotoRepository extends JpaRepository<Voto, Long> {

    @Query("SELECT v FROM Voto v WHERE v.associadoId = :associadoId AND v.pauta.id = :pautaId")
    Optional<Voto> findByAssociadoIdAndPautaId(@Param("associadoId") Long associadoId, @Param("pautaId") Long pautaId);

    @Query("SELECT v FROM Voto v WHERE v.pauta.id = :pautaId")
    List<Voto> findByPautaId(@Param("pautaId") Long pautaId);
}
