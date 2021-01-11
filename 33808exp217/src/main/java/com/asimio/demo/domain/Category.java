package com.asimio.demo.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category", schema = "public")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "category_id", unique = true, nullable = false)
    private int categoryId;

    @Column(name = "name", nullable = false, length = 25)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update", nullable = false, length = 29)
    private Date lastUpdate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "category")
    private Set<FilmCategory> filmCategories;
}