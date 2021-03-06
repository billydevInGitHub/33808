package com.asimio.demo.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "language", schema = "public")
@Getter
@Setter
public class Language implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "language_id", unique = true, nullable = false)
    private int languageId;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update", nullable = false, length = 29)
    private Date lastUpdate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "language")
    private Set<Film> films = new HashSet<Film>(0);
}