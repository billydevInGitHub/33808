package com.billydev.blib.b33808.exp006;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillyRestController {

	
	@GetMapping("/")
	String sessionid(HttpSession session) {
		return session.getId();
	}
	
}
