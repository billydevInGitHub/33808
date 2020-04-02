package com.billydev.blib.b33808.exp005;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BillyController {

	@GetMapping("/")
	public String helloWorld() {
		return "Hello, world123";
	}

}
