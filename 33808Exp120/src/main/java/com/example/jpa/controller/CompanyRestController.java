package com.example.jpa.controller;

import com.example.jpa.model.Company;
import com.example.jpa.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyRestController {

	@Autowired
	private CompanyService companyService;

	@GetMapping("/company")
	public ResponseEntity<List<Company>> getCompanyList() {
		return new ResponseEntity<List<Company>>(companyService.getCompanyList(), HttpStatus.OK);
	}

}
