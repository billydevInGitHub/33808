package com.asimio.demo.rest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asimio.demo.domain.Film;
import com.asimio.demo.rest.exception.ResourceNotFoundException;
import com.asimio.demo.rest.mapper.FilmResourceMapper;
import com.asimio.demo.rest.model.FilmResource;
import com.asimio.demo.service.DvdRentalService;
import com.asimio.demo.service.FilmSearchCriteria;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/films", produces = MediaType.APPLICATION_JSON_VALUE)
public class FilmController {

    private final DvdRentalService dvdRentalService;

    @GetMapping(path = "")
    public ResponseEntity<List<FilmResource>> retrieveFilms(

            @RequestParam(required = false)
            Optional<BigDecimal> minRentalRate,

            @RequestParam(required = false)
            Optional<BigDecimal> maxRentalRate,

            @RequestParam(required = false)
            Optional<Long> releaseYear,

            @RequestParam(name="category", required = false)
            Set<String> categories) {

        FilmSearchCriteria searchCriteria = FilmSearchCriteria.builder()
                .minRentalRate(minRentalRate)
                .maxRentalRate(maxRentalRate)
                .releaseYear(releaseYear)
                .categories(categories)
                .build();

        List<Film> films = this.dvdRentalService.retrieveFilms(searchCriteria);
        List<FilmResource> result = FilmResourceMapper.INSTANCE.map(films);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FilmResource> retrieveFilm(@PathVariable Integer id) {
        Optional<Film> optionalFilm = this.dvdRentalService.retrieveFilm(id);
        return optionalFilm.map(film -> {
            FilmResource resource = FilmResourceMapper.INSTANCE.map(film);
            return new ResponseEntity<>(resource, HttpStatus.OK);
        }).orElseThrow(() -> new ResourceNotFoundException(String.format("Film with id=%s not found", id)));
    }
}