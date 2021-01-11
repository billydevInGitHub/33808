package com.asimio.demo.service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FilmSearchCriteria {

    private Optional<BigDecimal> minRentalRate;
    private Optional<BigDecimal> maxRentalRate;
    private Optional<Long> releaseYear;
    private Set<String> categories;
}