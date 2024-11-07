package com.brandon.desafio_tecnico_nt.model;

import jakarta.persistence.*;

@Entity
@Table(name = "votos")
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long associadoId;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;


    private Boolean voto;


    public Voto() {}

    public Voto(Long associadoId, Pauta pauta, Boolean voto) {
        this.associadoId = associadoId;
        this.pauta = pauta;
        this.voto = voto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAssociadoId() {
        return associadoId;
    }

    public void setAssociadoId(Long associadoId) {
        this.associadoId = associadoId;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public Boolean getVoto() {
        return voto;
    }

    public void setVoto(Boolean voto) {
        this.voto = voto;
    }
}

