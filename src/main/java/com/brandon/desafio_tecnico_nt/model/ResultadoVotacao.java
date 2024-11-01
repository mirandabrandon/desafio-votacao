package com.brandon.desafio_tecnico_nt.model;

public class ResultadoVotacao {

    private long votosSim;
    private long votosNao;

    public ResultadoVotacao(long votosSim, long votosNao) {
        this.votosSim = votosSim;
        this.votosNao = votosNao;
    }

    public long getVotosSim() {
        return votosSim;
    }

    public long getVotosNao() {
        return votosNao;
    }
}

