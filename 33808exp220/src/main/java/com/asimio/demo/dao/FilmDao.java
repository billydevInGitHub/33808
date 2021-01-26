package com.asimio.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import com.asimio.demo.domain.Film;

/**
 * Extending {@link JpaSpecificationExecutor} allows execution of {@see FilmSpecifications} {@link Specification}s based on the JPA criteria API.
 *
 * @author Orlando L Otero
 */
@Repository
public interface FilmDao extends JpaRepository<Film, Integer>, JpaSpecificationExecutor<Film> {

    @EntityGraph(
            type = EntityGraphType.FETCH,
            attributePaths = {
                    "language", 
                    "filmActors", "filmActors.actor"
            }
    )
    List<Film> findAll(@Nullable Specification<Film> spec);

    @EntityGraph(
            type = EntityGraphType.FETCH,
            attributePaths = {
                    "language", 
                    "filmActors", "filmActors.actor"
            }
    )
    Optional<Film> findById(Integer id);
}