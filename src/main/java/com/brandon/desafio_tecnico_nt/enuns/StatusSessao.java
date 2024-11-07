package com.brandon.desafio_tecnico_nt.enuns;

public enum StatusSessao {
    ATIVA("ATIVA", "Ativa"),
    INATIVA("inativa", "Inativa");

    private String codigo;
    private String descricao;

    StatusSessao(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
