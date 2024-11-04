package com.brandon.desafio_tecnico_nt.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sessoes_votacao")
public class SessaoVotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "pauta_id", referencedColumnName = "id")
    private Pauta pauta;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    private Integer duracao; // em minutos

    private Boolean ativa;


    public SessaoVotacao() {}

    public SessaoVotacao(Pauta pauta, Integer duracao) {
        this.pauta = pauta;
        this.duracao = duracao;
        this.dataInicio = LocalDateTime.now();
        this.dataFim = this.dataInicio.plusMinutes(duracao != null ? duracao : 1);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataAbertura) {
        this.dataInicio = dataAbertura;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFechamento) {
        this.dataFim = dataFechamento;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public Boolean getAtiva() {
        return ativa;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }
}

