package com.example.domingas.teste.Modelo;

public class Direccao {
    private String uid;
    private String cargo;
    private String Nome;

    public Direccao() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    @Override
    public String toString() {
        return "Direccao{" +
                "Nome='" + Nome + '\'' +
                '}';
    }
}


