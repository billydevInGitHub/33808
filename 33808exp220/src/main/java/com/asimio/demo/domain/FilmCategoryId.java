package com.asimio.demo.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FilmCategoryId implements Serializable {

    private static final long serialVersionUID = -7987680183531561896L;

    @Column(name = "film_id", nullable = false)
    @EqualsAndHashCode.Include
    private short filmId;

    @Column(name = "category_id", nullable = false)
    @EqualsAndHashCode.Include
    private short categoryId;
}