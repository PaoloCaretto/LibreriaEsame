package com.example.LibreriaEsame.LibreriaEsame.models;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.example.LibreriaEsame.LibreriaEsame.models.Utente;


@Entity
public class Libro {
    public Integer getId() {
        return Id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @NotNull
    @Size(min=1, max=100)
    private String titolo;
    @NotNull
    @Size(min=2, max=100)
    private String autore;
    @NotNull(message = "Devi inserire un valore")
    private float prezzo;
    @NotNull
    private Integer annoPubblicazione;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public Integer getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(Integer annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Libro() {}
    public Libro(String titolo, String autore, float prezzo, Integer annoPubblicazione) {
        this.titolo = titolo;
        this.autore = autore;
        this.prezzo = prezzo;
        this.annoPubblicazione = annoPubblicazione;
    }
}
