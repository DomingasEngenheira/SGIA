package com.example.domingas.teste.Modelo;

public class Usuario {
    private String uid;
    private String email;
    private String Senha;

    public Usuario() {
        ;
    }

    public String getUid() {
        return uid;
    }



    public String getEmail() {
        return email;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    @Override
    public String toString() {
        return "Email{" +
                "email='" + email + '\'' +
                '}';
    }
}
