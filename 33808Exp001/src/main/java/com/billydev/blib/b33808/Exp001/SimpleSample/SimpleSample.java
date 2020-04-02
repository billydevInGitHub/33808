package com.billydev.blib.b33808.Exp001.SimpleSample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleSample implements CommandLineRunner {

	@Autowired
	private BillyService billyService; 
	
	public static void main(String[] args) {
		SpringApplication.run(SimpleSample.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(billyService.getHelloMessage());
		
	}

}
