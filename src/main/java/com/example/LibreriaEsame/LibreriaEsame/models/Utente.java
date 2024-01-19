package com.example.LibreriaEsame.LibreriaEsame.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    @NotNull
    @Size(min=1, max=100)
    private String nome;
    @NotNull
    @Size(min=1, max=100)
    private String cognome;
    @NotNull
    @Size(min=1, max=20)
    @Column(unique = true)
    private String username;
    @NotNull
    @Size(min=5, max=30)
    private String password;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
    private List<Libro> libri;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Utente(){}

    public Utente(String nome, String cognome, String username, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.libri = new ArrayList<Libro>();
    }
}
