package com.devglan.actuatordemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ActuatorDemoApplication {

	private static Logger log = LoggerFactory.getLogger(ActuatorDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ActuatorDemoApplication.class, args);
		
	}

	@GetMapping("/greeting")
	public String greet(){
		log.info("this is logging info ");
		log.debug("this is logging debug");
		log.trace("this is logging trace");
		return "Hello there!";
	}

}
