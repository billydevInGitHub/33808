package com.asimio.demo.service;

import java.util.List;
import java.util.Optional;

import com.asimio.demo.domain.Film;

public interface DvdRentalService {

    List<Film> retrieveFilms(FilmSearchCriteria searchCriteria);

    Optional<Film> retrieveFilm(Integer id);
}