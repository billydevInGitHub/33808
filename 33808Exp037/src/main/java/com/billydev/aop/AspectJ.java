package com.billydev.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class AspectJ {
    //@Pointcut could be reused
    @Pointcut("execution(* com.billydev.service.IndexService.*(..))")
    public void testPointCut(){
        System.out.println("this is from aop pointcut********");
    }

    @Before("com.billydev.aop.AspectJ.testPointCut()")
    public void beforeAdvice(){
        System.out.println("print from before advice!");
    }

      @After("execution(* com.billydev.service.*.*(..))")
      public void testAdvicePointCut(){
          System.out.println("this is from aop !!!!");
     }
}
