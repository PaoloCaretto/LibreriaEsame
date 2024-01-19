package com.example.LibreriaEsame.LibreriaEsame.controllers;

import com.example.LibreriaEsame.LibreriaEsame.models.Libro;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.LibreriaEsame.LibreriaEsame.libriDao.LibriRepository;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class ControllerApi {
    @Autowired
    private LibriRepository libriRepository;

    Gson gson = new GsonBuilder()
            .create();

    @GetMapping("tuttiLibri")
    public String getTuttiLibri() {
        String risultato = gson.toJson(libriRepository.findAll());
        return risultato;
    }

    @GetMapping("/libri/titolo/{titolo}")
    public String getBookByTitle(@PathVariable("titolo") String titolo) {
        Libro libro = libriRepository.findByTitle(titolo);
        if (libro == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
        String risultato = gson.toJson(libro);
        return risultato;
    }



}

