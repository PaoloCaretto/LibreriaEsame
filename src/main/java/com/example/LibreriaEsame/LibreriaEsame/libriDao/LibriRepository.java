package com.example.LibreriaEsame.LibreriaEsame.libriDao;

import com.example.LibreriaEsame.LibreriaEsame.models.Libro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LibriRepository extends CrudRepository<Libro, Integer> {
    @Query("select a from Libro a where titolo = :titolo")
    public Libro findByTitle(String titolo);
}
