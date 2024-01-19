package com.example.LibreriaEsame.LibreriaEsame.libriDao;

import com.example.LibreriaEsame.LibreriaEsame.models.Utente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UtentiRepository extends CrudRepository<Utente, Integer> {
    List<Utente> findByUsername(String username);
    @Query("select s from Utente s where username = :username and password = :password")
    public Utente login(String username, String password);

}
