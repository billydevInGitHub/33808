package com.example.jpa.model;

import javax.persistence.*;

@Entity
@Table(name = "b33808exp237_DataEntity")
public class DataEntity {
    @Id
    private Long id;

    public DataEntity(){

    }

    @Column(unique = true)
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DataEntity(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
