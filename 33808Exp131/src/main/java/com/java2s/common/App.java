package com.java2s.common;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
        "applicationContext.xml");
    PersonDaoImpl dao = (PersonDaoImpl) context.getBean("personDao");

    dao.test();

    context.close();
    
    Helper.checkData();
  }
}
