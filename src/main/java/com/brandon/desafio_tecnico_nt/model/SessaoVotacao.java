package com.brandon.desafio_tecnico_nt.model;

import com.brandon.desafio_tecnico_nt.enuns.StatusSessao;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sessoes_votacao")
public class SessaoVotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pauta_id", referencedColumnName = "id")
    private Pauta pauta;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    private Integer duracao; // em minutos

    private StatusSessao status;


    public SessaoVotacao() {}

    public SessaoVotacao(Pauta pauta, Integer duracao, StatusSessao status) {
        this.pauta = pauta;
        this.duracao = duracao;
        this.dataInicio = LocalDateTime.now();
        this.dataFim = this.dataInicio.plusMinutes(duracao != null ? duracao : 1);
        this.status = status;
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

    public StatusSessao getStatus() {
        return status;
    }

    public void setStatus(StatusSessao status) {
        this.status = status;
    }
}

