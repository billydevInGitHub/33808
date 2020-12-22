package com.jdriven.hotels;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class HotelsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelsApplication.class, args);
	}

	@GetMapping("/test")
	@ResponseBody
	public String getIndex() {
		return "this is from 33808exp205";
	}

	@GetMapping("/whoami")
//	@GetMapping("/")
	@ResponseBody
	public Authentication whoami(Authentication auth) {
		return auth;
	}

}
