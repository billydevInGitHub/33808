package com.billydev;


import com.billydev.service.IndexService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class AopBasic {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AopBasic.class);
        IndexService indexService = (IndexService)annotationConfigApplicationContext.getBean("indexService");
        indexService.service();


    }

}
