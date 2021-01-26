package com.asimio.demo.rest.mapper;

import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import com.asimio.demo.domain.Film;
import com.asimio.demo.fixtures.FilmFixtures;
import com.asimio.demo.rest.model.Actor;
import com.asimio.demo.rest.model.FilmResource;

public class FilmResourceMapperTest {

    @Test
    public void shouldMapFromFilmToFilmResource() {
        // Given
        Film film = FilmFixtures.createFilm();
        // When
        FilmResource actual = FilmResourceMapper.INSTANCE.map(film);
        // Then
        assertFilmResource(actual);
    }

    private void assertFilmResource(FilmResource actual) {
        Assert.assertThat(actual.getFilmId(), Matchers.equalTo(10));
        Assert.assertThat(actual.getTitle(), Matchers.equalTo("Title"));
        Assert.assertThat(actual.getDescription(), Matchers.equalTo("Description"));
        Assert.assertThat(actual.getReleaseYear(), Matchers.equalTo("1990"));
        Assert.assertThat(actual.getLength().intValue(), Matchers.equalTo(90));
        Assert.assertThat(actual.getRentalRate(), Matchers.equalTo(BigDecimal.valueOf(4.50)));
        Assert.assertThat(actual.getRentalDuration().intValue(), Matchers.equalTo(48));

        Assert.assertThat(actual.getLang(), Matchers.equalTo("English"));

        Assert.assertThat(actual.getActors(), Matchers.containsInAnyOrder(
                new Actor("First3", "Last3"), new Actor("First2", "Last2"))
        );
    }
}