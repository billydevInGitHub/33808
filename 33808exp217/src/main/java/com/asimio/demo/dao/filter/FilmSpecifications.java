package com.asimio.demo.dao.filter;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import javax.persistence.criteria.Join;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import com.asimio.demo.domain.Category;
import com.asimio.demo.domain.Category_;
import com.asimio.demo.domain.Film;
import com.asimio.demo.domain.FilmCategory;
import com.asimio.demo.domain.FilmCategory_;
import com.asimio.demo.domain.Film_;
import com.asimio.demo.service.FilmSearchCriteria;

public final class FilmSpecifications {

    private FilmSpecifications() {
    }

    public static Specification<Film> createFilmSpecifications(FilmSearchCriteria searchCriteria) {
        return rentalRateBetween(searchCriteria.getMinRentalRate(), searchCriteria.getMaxRentalRate())
                .and(releaseYearEqualTo(searchCriteria.getReleaseYear()))
                .and(categoryIn(searchCriteria.getCategories()));
    }

    public static Specification<Film> rentalRateBetween(Optional<BigDecimal> minRate, Optional<BigDecimal> maxRate) {
        return (root, query, builder) -> {
            return minRate.map(min -> {
                return maxRate.map(max -> builder.between(root.get(Film_.rentalRate), min, max)
                    ).orElse(null);
            }).orElse(null);
        };
    }

    public static Specification<Film> releaseYearEqualTo(Optional<Long> releaseYear) {
        return (root, query, builder) -> {
            return releaseYear.map(relYear -> builder.equal(root.get(Film_.releaseYear), String.valueOf(relYear))
                    ).orElse(null);
        };
    }

    public static Specification<Film> categoryIn(Set<String> categories) {
        if (CollectionUtils.isEmpty(categories)) {
            return null;
        }
        return (root, query, builder) -> {
            Join<Film, FilmCategory> filmCategoryJoin = root.join(Film_.filmCategories);
            Join<FilmCategory, Category> categoryJoin = filmCategoryJoin.join(FilmCategory_.category);
            return categoryJoin.get(Category_.name).in(categories);
        };
    }
}