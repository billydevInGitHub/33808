package com.asimio.demo.fixtures;

import java.util.Date;

import com.asimio.demo.domain.Category;

public class CategoryFixtures {

    public static Category createCategory(int categoryId, String name, Date lastUpdated) {

        return Category.builder()
                .categoryId(categoryId)
                .name(name)
                .lastUpdate(lastUpdated)
                .build();
    }
}