package com.asimio.demo.domain;

import java.math.BigDecimal;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Film.class)
public class Film_ {

    public static volatile SingularAttribute<Film, BigDecimal> rentalRate;
    public static volatile SingularAttribute<Film, String> releaseYear;
    public static volatile SetAttribute<Film, FilmCategory> filmCategories;
}