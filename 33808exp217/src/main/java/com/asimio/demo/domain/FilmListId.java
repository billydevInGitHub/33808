package com.asimio.demo.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * FilmListId generated by hbm2java
 */
@Embeddable
public class FilmListId  implements java.io.Serializable {


     private Integer fid;
     private String title;
     private String description;
     private String category;
     private BigDecimal price;
     private Short length;
     private String rating;
     private String actors;

    public FilmListId() {
    }

    public FilmListId(Integer fid, String title, String description, String category, BigDecimal price, Short length, String rating, String actors) {
       this.fid = fid;
       this.title = title;
       this.description = description;
       this.category = category;
       this.price = price;
       this.length = length;
       this.rating = rating;
       this.actors = actors;
    }
   

    @Column(name="fid")
    public Integer getFid() {
        return this.fid;
    }
    
    public void setFid(Integer fid) {
        this.fid = fid;
    }

    @Column(name="title")
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name="description")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name="category", length=25)
    public String getCategory() {
        return this.category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }

    @Column(name="price", precision=4)
    public BigDecimal getPrice() {
        return this.price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name="length")
    public Short getLength() {
        return this.length;
    }
    
    public void setLength(Short length) {
        this.length = length;
    }

    @Column(name="rating")
    public String getRating() {
        return this.rating;
    }
    
    public void setRating(String rating) {
        this.rating = rating;
    }

    @Column(name="actors")
    public String getActors() {
        return this.actors;
    }
    
    public void setActors(String actors) {
        this.actors = actors;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof FilmListId) ) return false;
		 FilmListId castOther = ( FilmListId ) other; 
         
		 return ( (this.getFid()==castOther.getFid()) || ( this.getFid()!=null && castOther.getFid()!=null && this.getFid().equals(castOther.getFid()) ) )
 && ( (this.getTitle()==castOther.getTitle()) || ( this.getTitle()!=null && castOther.getTitle()!=null && this.getTitle().equals(castOther.getTitle()) ) )
 && ( (this.getDescription()==castOther.getDescription()) || ( this.getDescription()!=null && castOther.getDescription()!=null && this.getDescription().equals(castOther.getDescription()) ) )
 && ( (this.getCategory()==castOther.getCategory()) || ( this.getCategory()!=null && castOther.getCategory()!=null && this.getCategory().equals(castOther.getCategory()) ) )
 && ( (this.getPrice()==castOther.getPrice()) || ( this.getPrice()!=null && castOther.getPrice()!=null && this.getPrice().equals(castOther.getPrice()) ) )
 && ( (this.getLength()==castOther.getLength()) || ( this.getLength()!=null && castOther.getLength()!=null && this.getLength().equals(castOther.getLength()) ) )
 && ( (this.getRating()==castOther.getRating()) || ( this.getRating()!=null && castOther.getRating()!=null && this.getRating().equals(castOther.getRating()) ) )
 && ( (this.getActors()==castOther.getActors()) || ( this.getActors()!=null && castOther.getActors()!=null && this.getActors().equals(castOther.getActors()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getFid() == null ? 0 : this.getFid().hashCode() );
         result = 37 * result + ( getTitle() == null ? 0 : this.getTitle().hashCode() );
         result = 37 * result + ( getDescription() == null ? 0 : this.getDescription().hashCode() );
         result = 37 * result + ( getCategory() == null ? 0 : this.getCategory().hashCode() );
         result = 37 * result + ( getPrice() == null ? 0 : this.getPrice().hashCode() );
         result = 37 * result + ( getLength() == null ? 0 : this.getLength().hashCode() );
         result = 37 * result + ( getRating() == null ? 0 : this.getRating().hashCode() );
         result = 37 * result + ( getActors() == null ? 0 : this.getActors().hashCode() );
         return result;
   }   


}


