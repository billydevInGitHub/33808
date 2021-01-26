package com.asimio.demo.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import com.asimio.demo.dao.filter.FilmSpecifications;
import com.asimio.demo.domain.Film;
import com.asimio.demo.service.FilmSearchCriteria;
import com.google.common.collect.Sets;

/**
 * @author Orlando L Otero - https://tech.asimio.net
 * {@see FilmControllerIntegrationTest}
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("integration-test")
@SqlGroup({
    @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = { "classpath:schema.sql", "classpath:data.sql" }),
    @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:purge.sql")
})
public class FilmDaoTest {

    private static final Integer[] FILM_IDS = new Integer[] { 19, 38, 97, 105, 111, 119, 130, 178, 194, 205, 242, 247,
            250, 287, 292, 303, 317, 318, 332, 335, 360, 388, 395, 404, 417, 511, 524, 549, 555, 574, 586, 594, 604,
            613, 697, 717, 732, 748, 794, 850, 906, 932, 978, 982 };

    @Autowired
    private FilmDao filmDao;

    @Test
    public void shouldRetrieve44Films() {
        // Given
        FilmSearchCriteria searchCriteria = FilmSearchCriteria.builder()
                .minRentalRate(Optional.of(BigDecimal.valueOf(0.99)))
                .maxRentalRate(Optional.of(BigDecimal.valueOf(1.99)))
                .releaseYear(Optional.of(2006L))
                .categories(Sets.newHashSet("Action", "Comedy"))
                .build();
        Specification<Film> specs = FilmSpecifications.createFilmSpecifications(searchCriteria);

        // When
        List<Film> actual = this.filmDao.findAll(specs);

        // Then
        Assert.assertThat(actual.size(), Matchers.is(44));
        Set<Integer> filmIds = actual.stream()
                .map(Film::getFilmId)
                .collect(Collectors.toSet());
        Assert.assertThat(filmIds, Matchers.containsInAnyOrder(FILM_IDS));
        // More assertions
    }
}