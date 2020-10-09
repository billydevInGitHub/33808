package com.example.authenticatingldap;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
@RequestMapping("/api")
public class HomeController {

	public static final String BASIC_AUTH_SECURITY_SCHEME = "basicAuth";

	@GetMapping("/")
	public String index() {
		return "Welcome to the home page!";
	}

	@Operation(summary = "Get string from public endpoint")
	@GetMapping("/public")
	public String getPublicString() {
		return "It is public.\n";
	}

	@Operation(
			summary = "Get string from private/secured endpoint",
			security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
	@GetMapping("/private")
	public String getPrivateString(Principal principal) {
		return principal.getName() + ", it is private.\n";
	}
}
