package com.brandon.desafio_tecnico_nt.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private Boolean ativo;

    public User() {
    }

    public User(String nome, String senha, Boolean ativo) {
        this.nome = nome;
        this.senha = senha;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String username) {
        this.nome = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String password) {
        this.senha = password;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean enabled) {
        this.ativo = enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + nome + '\'' +
                ", enabled=" + ativo +
                '}';
    }
}


