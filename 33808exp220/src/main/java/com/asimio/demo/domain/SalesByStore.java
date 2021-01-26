package com.asimio.demo.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * SalesByStore generated by hbm2java
 */
@Entity
@Table(name="sales_by_store"
    ,schema="public"
)
public class SalesByStore  implements java.io.Serializable {


     private SalesByStoreId id;

    public SalesByStore() {
    }

    public SalesByStore(SalesByStoreId id) {
       this.id = id;
    }
   
     @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="store", column=@Column(name="store") ), 
        @AttributeOverride(name="manager", column=@Column(name="manager") ), 
        @AttributeOverride(name="totalSales", column=@Column(name="total_sales", precision=131089, scale=0) ) } )
    public SalesByStoreId getId() {
        return this.id;
    }
    
    public void setId(SalesByStoreId id) {
        this.id = id;
    }




}


