package com.billydev.blib.b33808.Exp002;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
@EnableAutoConfiguration
public class BillyServletApplication {

	@Bean
	public Servlet dispatcherServlet() {
		return new GenericServlet() {
			@Override
			public void service(ServletRequest req, ServletResponse res)
					throws ServletException, IOException {
				res.setContentType("text/plain");
				res.getWriter().append("Hello World");
			}
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BillyServletApplication.class, args);
	}

}
