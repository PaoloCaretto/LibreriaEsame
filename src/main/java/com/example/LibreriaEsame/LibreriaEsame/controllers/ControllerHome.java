package com.example.LibreriaEsame.LibreriaEsame.controllers;

import com.example.LibreriaEsame.LibreriaEsame.libriDao.UtentiRepository;
import com.example.LibreriaEsame.LibreriaEsame.models.Utente;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class ControllerHome {
    @Autowired
    private UtentiRepository utentiRepository;

    @GetMapping("/")
    public String getHomepage(HttpSession session) {
        Utente utente = (Utente) session.getAttribute("utenteLoggato");
        if(utente == null) {
            return "redirect:/utente/login";
        } else {
            return "home";
        }
    }
}
