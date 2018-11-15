package com.example.domingas.teste.Modelo;

public class membro {
    private  String UID;
    private String nome;
    private String email;
    private String senha;
    private String estado;
    private String morada;
    private String natural;

    public membro() {
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha(String s) {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEstado(String s) {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMorada(String s) {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getNatural(String s) {
        return natural;
    }

    public void setNatural(String natural) {
        this.natural = natural;
    }

    @Override
    public String toString() {
        return nome;
    }
}
