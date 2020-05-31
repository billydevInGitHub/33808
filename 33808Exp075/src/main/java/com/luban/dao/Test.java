package com.luban.dao;

import com.luban.app.AppConfig;
import com.luban.service.CardService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
	public static void main(String[] args) {


		AnnotationConfigApplicationContext annotationConfigApplicationContext	= new AnnotationConfigApplicationContext(AppConfig.class);
		annotationConfigApplicationContext.getBean(CardService.class).list();

		CardDao dao= (CardDao) annotationConfigApplicationContext.getBean("cardDao");
		dao.getList("----");
	}
}
