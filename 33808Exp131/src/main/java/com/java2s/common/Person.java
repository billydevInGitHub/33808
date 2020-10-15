package com.java2s.common;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity
public class Person {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  private long id;
	 
	private String name;
	
	@ManyToOne
  @JoinColumns({
    @JoinColumn(name="DEPT_NAME", referencedColumnName="Name"),
    @JoinColumn(name="DEPT_ID", referencedColumnName="ID")
  })

  private Department department;
	
  public Person() {}

	public Person(String name) {
		this.name = name;
	}


  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

  @Override
  public String toString() {
    return "Person [id=" + id + ", name=" + name + "]";
  }

}