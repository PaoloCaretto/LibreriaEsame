package com.example.LibreriaEsame.LibreriaEsame.controllers;

import com.example.LibreriaEsame.LibreriaEsame.libriDao.LibriRepository;
import com.example.LibreriaEsame.LibreriaEsame.models.Libro;
import com.example.LibreriaEsame.LibreriaEsame.models.Utente;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/libri")
public class ControllerLibri {
    @Autowired
    private LibriRepository libriRepository;

    @GetMapping("/aggiungiLibro")
    public String getAggiungiL(Libro libro, HttpSession session) {
        Utente utente = (Utente) session.getAttribute("utenteLoggato");
        if (utente == null) {
            return "redirect:/utente/login";
        }
        return "aggiungilibro";
    }

    @PostMapping("/aggiungiLibro")
    public String postAggiungiL(@Valid Libro libro, BindingResult bindingResult, HttpSession session) {
        Utente utente = (Utente) session.getAttribute("utenteLoggato");
        if (utente == null) {
            return "redirect:/utente/login";
        }
        if (bindingResult.hasErrors()){
            return "aggiungilibro";
        }
        libro.setUtente(utente);
        libriRepository.save(libro);
        return "messaggiolibroaggiunto";
    }

    @GetMapping("/tuttiLibri")
    ModelAndView getTuttiLibri(HttpSession session) {
        Utente utente = (Utente) session.getAttribute("utenteLoggato");
        if (utente == null) {
            return new ModelAndView(new RedirectView("/utente/login"));
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tuttiLibri");
        modelAndView.addObject("libri", libriRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/mieiLibri")
    ModelAndView getMieiLibri(HttpSession session) {
        Utente utente = (Utente) session.getAttribute("utenteLoggato");
        if (utente == null) {
            return new ModelAndView(new RedirectView("/utente/login"));
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mieiLibri");
        Iterable<Libro> tuttiLibri = libriRepository.findAll();
        List<Libro> mieiLibri = new ArrayList<>();
        for (Libro libro : tuttiLibri) {
            if (libro.getUtente().getId().equals(utente.getId())) {
                mieiLibri.add(libro);
            }
        }
        modelAndView.addObject("libri", mieiLibri);
        return modelAndView;
    }

    @GetMapping("/dettaglio/{id}")
    ModelAndView getDettaglioLibro(@PathVariable("id") int id, HttpSession session) {
        Utente utente = (Utente) session.getAttribute("utenteLoggato");
        if (utente == null) {
            return new ModelAndView(new RedirectView("/utente/login"));
        }
        Libro libro = libriRepository.findById(id).orElse(null);
        boolean proprietario = false;
        if (libro == null) {
            return null;
        }
        if (libro.getUtente().getId().equals(utente.getId())) {
            proprietario = true;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dettaglioLibro");
        modelAndView.addObject("libro", libro);
        modelAndView.addObject("proprietario", proprietario);
        return modelAndView;
    }

    @PostMapping("/rimuovi/{id}")
    String postRimuoviLibro(@PathVariable("id") int id, HttpSession session) {
        Utente utente = (Utente) session.getAttribute("utenteLoggato");
        if (utente == null) {
            return "/utente/login";
        }
        Libro libro = libriRepository.findById(id).orElse(null);
        if (libro == null) {
            return "redirect:libri/tuttiLibri";
        }
        if (!libro.getUtente().getId().equals(utente.getId())) {
            return "redirect:/libri/dettaglio/" + id;
        }
        libriRepository.delete(libro);
        return "messaggiolibrorimosso";
    }
}
