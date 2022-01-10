package com.baeldung.spring.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class BootstrapTest {
    //billy add this class
    public static void main(String[] args) {
        AnnotationConfigApplicationContext  annotationConfigApplicationContext
                = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.refresh();
    }
}
