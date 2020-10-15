package com.java2s.common;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class PersonDaoImpl {
  public void test(){
    Department d = new Department();
    d.setName("Design");

    Person p1 = new Person("Tom");
    p1.setDepartment(d);

    Person p2 = new Person("Jack");
    p2.setDepartment(d);
    
    d.getPersons().add(p1);
    d.getPersons().add(p2);
    
    em.persist(p1);
    em.persist(p2);
    em.persist(d);

  }
	@PersistenceContext
	private EntityManager em;
}
