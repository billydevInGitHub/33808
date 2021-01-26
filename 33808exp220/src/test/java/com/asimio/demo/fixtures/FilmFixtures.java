package com.asimio.demo.fixtures;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.Set;

import org.mockito.internal.util.collections.Sets;

import com.asimio.demo.domain.Actor;
import com.asimio.demo.domain.Category;
import com.asimio.demo.domain.Film;
import com.asimio.demo.domain.FilmActor;
import com.asimio.demo.domain.FilmActorId;
import com.asimio.demo.domain.FilmCategory;
import com.asimio.demo.domain.FilmCategoryId;

public class FilmFixtures {

    private static final Integer DEFAULT_FILM_ID = Integer.valueOf(10);
    private static final String DEFAULT_TITLE = "Title";
    private static final String DEFAULT_DESCRIPTION = "Description";
    private static final String DEFAULT_REL_YEAR = "1990";
    private static final Short DEFAULT_LENGHT = 90; // minutes
    private static final BigDecimal DEFAULT_RENTAL_RATE = BigDecimal.valueOf(4.50); // daily
    private static final Short DEFAULT_RENTAL_DURATION = 48; // hours
    private static final Date DEFAULT_LAST_UPDATED_TMSTP = Date.from(Instant.ofEpochMilli(1514768400));

    public static Film createFilm() {
        return createFilm(DEFAULT_FILM_ID, DEFAULT_TITLE, DEFAULT_DESCRIPTION, DEFAULT_REL_YEAR, DEFAULT_LENGHT,
                DEFAULT_RENTAL_RATE, DEFAULT_RENTAL_DURATION, DEFAULT_LAST_UPDATED_TMSTP);
    }

    public static Film createFilm(int id, String title, String description, String relYear, Short lenght,
            BigDecimal rentalRate, Short rentalDuration, Date lastUpdated) {

        Film result = new Film();
        result.setFilmId(id);
        result.setTitle(title);
        result.setDescription(description);
//        result.setReleaseYear(relYear);
        result.setLength(lenght);
        result.setRentalRate(rentalRate);
        result.setRentalDuration(rentalDuration);
        Set<Film> films = Sets.newSet(result);
        result.setLanguage(LanguageFixtures.createLanguage(films));
        result.setLastUpdate(lastUpdated);
        // Film actors
        Actor actor1 = ActorFixtures.createActor(2, "First2", "Last2", DEFAULT_LAST_UPDATED_TMSTP);
        FilmActor filmActor1 = createFilmActor(actor1, result);
        Actor actor2 = ActorFixtures.createActor(3, "First3", "Last3", DEFAULT_LAST_UPDATED_TMSTP);
        FilmActor filmActor2 = createFilmActor(actor2, result);
        result.setFilmActors(Sets.newSet(filmActor1, filmActor2));
        // Film categories
        Category category1 = CategoryFixtures.createCategory(4, "Action", DEFAULT_LAST_UPDATED_TMSTP);
        FilmCategory filmCategory1 = createFilmCategory(category1, result);
        Category category2 = CategoryFixtures.createCategory(5, "Comedy", DEFAULT_LAST_UPDATED_TMSTP);
        FilmCategory filmCategory2 = createFilmCategory(category2, result);
        result.setFilmCategories(Sets.newSet(filmCategory1, filmCategory2));
        // Set more attributes
//        result.setFulltext(fullText);
//        result.setRating(rating);
//        result.setReplacementCost(replacementCost);
//        result.setSpecialFeatures(specialFeatures);
//        result.setInventories(inventories);
        return result;
    }

    public static FilmActor createFilmActor(Actor actor, Film film) {
        FilmActor result = new FilmActor();
        result.setActor(actor);
        result.setFilm(film);
        result.setLastUpdate(DEFAULT_LAST_UPDATED_TMSTP);
        result.setId(createFilmActorId(actor, film));
        return result;
    }

    private static FilmActorId createFilmActorId(Actor actor, Film film) {
        FilmActorId result = new FilmActorId();
        result.setActorId((short) actor.getActorId());
        result.setFilmId((short) film.getFilmId());
        return result;
    }

    public static FilmCategory createFilmCategory(Category category, Film film) {
        FilmCategory result = new FilmCategory();
        result.setCategory(category);
        result.setFilm(film);
        result.setLastUpdate(DEFAULT_LAST_UPDATED_TMSTP);
        result.setId(createFilmCategoryId(category, film));
        return result;
    }

    private static FilmCategoryId createFilmCategoryId(Category category, Film film) {
        FilmCategoryId result = new FilmCategoryId();
        result.setCategoryId((short) category.getCategoryId());
        result.setFilmId((short) film.getFilmId());
        return result;
    }
}