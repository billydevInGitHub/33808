package com.asimio.demo.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * CustomerList generated by hbm2java
 */
@Entity
@Table(name="customer_list"
    ,schema="public"
)
public class CustomerList  implements java.io.Serializable {


     private CustomerListId id;

    public CustomerList() {
    }

    public CustomerList(CustomerListId id) {
       this.id = id;
    }
   
     @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="id", column=@Column(name="id") ), 
        @AttributeOverride(name="name", column=@Column(name="name") ), 
        @AttributeOverride(name="address", column=@Column(name="address", length=50) ), 
        @AttributeOverride(name="zipCode", column=@Column(name="zip code", length=10) ), 
        @AttributeOverride(name="phone", column=@Column(name="phone", length=20) ), 
        @AttributeOverride(name="city", column=@Column(name="city", length=50) ), 
        @AttributeOverride(name="country", column=@Column(name="country", length=50) ), 
        @AttributeOverride(name="notes", column=@Column(name="notes") ), 
        @AttributeOverride(name="sid", column=@Column(name="sid") ) } )
    public CustomerListId getId() {
        return this.id;
    }
    
    public void setId(CustomerListId id) {
        this.id = id;
    }




}


