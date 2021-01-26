package com.asimio.demo.rest.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class FilmResource {

    private int filmId;
    private String lang;
    private String title;
    private String description;
    private BigDecimal rentalRate;
    private Short rentalDuration;
    private Short length;
    private String releaseYear;
    private List<Actor> actors;
}