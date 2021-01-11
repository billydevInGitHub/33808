package com.asimio.demo.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(FilmCategory.class)
public class FilmCategory_ {

    public static volatile SingularAttribute<FilmCategory, Category> category;
}