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

    private LocalDateTime dataAbertura;

    private LocalDateTime dataFechamento;

    private Integer duracao; // em minutos


    public SessaoVotacao() {}

    public SessaoVotacao(Pauta pauta, Integer duracao) {
        this.pauta = pauta;
        this.duracao = duracao;
        this.dataAbertura = LocalDateTime.now();
        this.dataFechamento = this.dataAbertura.plusMinutes(duracao != null ? duracao : 1);
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

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }
}

