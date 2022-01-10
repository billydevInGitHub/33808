package com.billydev.blib.b33808.Exp004;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@Controller
public class BillyController {

	
	@Value("${application.message}")
	private String message = "Hello Billy from Billy Controller ";

	@GetMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("time", new Date());
		model.put("message", this.message);
		return "Billy";
	}

	@RequestMapping("/fail")
	public String fail() {
		throw new MyException("Oh dear!");
	}

	@RequestMapping("/fail2")
	public String fail2() {
		throw new IllegalStateException();
	}

	@ExceptionHandler(MyException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody BillyRestResponse handleMyRuntimeException(MyException exception) {
		return new BillyRestResponse("Some data I want to send back to the client.");
	}

	
}
