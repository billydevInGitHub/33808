package com.billydev.entity;


import java.io.Serializable;
import java.util.Date;

/**
* <p>
    * 
    * </p>
*
* @author 
* @since 2019-05-23
*/
    public class User implements Serializable {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private static final long serialVersionUID = 1L;

    int id;
    String name;
    String email;


}
