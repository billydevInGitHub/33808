package com.example.jpa.service;


import com.example.jpa.model.Company;
import com.example.jpa.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	public List<Company> getCompanyList() {
		return companyRepository.findAll();
	}

}
