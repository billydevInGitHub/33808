package com.billydev.blib.b33808;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BillySecondController {

	
	
	@GetMapping("/second")
	@ResponseBody
	public String helloWorld() {
		return "Hello world from second controller";
	}
}
