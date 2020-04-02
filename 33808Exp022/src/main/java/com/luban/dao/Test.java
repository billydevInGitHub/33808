package com.luban.dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext  annotationConfigApplicationContext
                = new AnnotationConfigApplicationContext(Spring.class);
        IndexService indexService=(IndexService)annotationConfigApplicationContext.getBean("indexService");
        indexService.service();
    }
}


