package com.asimio.demo.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Category.class)
public class Category_ {

    public static volatile SingularAttribute<Category, String> name;
}