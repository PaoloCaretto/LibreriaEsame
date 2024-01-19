package com.example.LibreriaEsame.LibreriaEsame.controllers;

import com.example.LibreriaEsame.LibreriaEsame.libriDao.UtentiRepository;
import com.example.LibreriaEsame.LibreriaEsame.models.Utente;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping(path = "/utente")
public class ControllerUtenti {
    @Autowired
    private UtentiRepository utentiRepository;

    @GetMapping("/registrazione")
    public String getRegistrazione(Utente utente) {
        return "registrazione";
    }

    @PostMapping("/registrazione")
    public String postRegistrazione(@Valid Utente utente, BindingResult bindingResult, HttpSession session) {
        if(bindingResult.hasErrors()){
            return "registrazione";
        }
        utentiRepository.save(utente);
        session.setAttribute("utenteLoggato", utente);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String getLogin(Utente utente) {
        return "accesso";
    }

    @PostMapping("/login")
    public String postLogin(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        Utente utente = utentiRepository.login(username, password);
        if (utente == null) {
            return "accesso";
        } else {
            session.setAttribute("utenteLoggato", utente);
            return "redirect:/";
        }
    }

    @PostMapping("/logout")
    public String postLogout(HttpSession session) {
        session.setAttribute("utenteLoggato", null);
        return "redirect:/utente/login";
    }

    @GetMapping("/dettaglio")
    public ModelAndView getDettaglioUtente(HttpSession session) {
        Utente utente = (Utente) session.getAttribute("utenteLoggato");
        if (utente == null) {
            return new ModelAndView(new RedirectView("/utente/login"));
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dettaglioUtente");
        modelAndView.addObject("utente", utente);
        return modelAndView;
    }



}
