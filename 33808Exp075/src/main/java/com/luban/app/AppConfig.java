package com.luban.app;

import com.luban.anno.LubanScan;
import com.luban.test.MyImportBeanDefinitionRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

@Configurable
@ComponentScan("com.luban")
@LubanScan
public class AppConfig {
//	@Bean
//	@Autowired
//	public  String getString(){
//		System.out.println("this is mimic sql session factory bean");
//		return new String("123");
//	}

//	@Bean
//	public DataSource dataSource(){
//		return null;
//	}

}
