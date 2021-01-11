package com.asimio.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asimio.demo.dao.FilmDao;
import com.asimio.demo.dao.filter.FilmSpecifications;
import com.asimio.demo.domain.Film;
import com.asimio.demo.service.DvdRentalService;
import com.asimio.demo.service.FilmSearchCriteria;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class DefaultDvdRentalService implements DvdRentalService {

    private final FilmDao filmDao;

    @Override
    public List<Film> retrieveFilms(FilmSearchCriteria searchCriteria) {
        Specification<Film> filmSpecifications = FilmSpecifications.createFilmSpecifications(searchCriteria);
        return this.filmDao.findAll(filmSpecifications);
    }

    @Override
    public Optional<Film> retrieveFilm(Integer id) {
        return this.filmDao.findById(id);
    }
}