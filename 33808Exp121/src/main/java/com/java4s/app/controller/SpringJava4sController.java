/* 
 * Author ::. Sivateja Kandula | www.java4s.com 
 *
 */

package com.java4s.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java4s.app.repository.SpringJava4sDAO;
import com.java4s.model.Customer;

@RestController
public class SpringJava4sController {
	
	@Autowired
	public SpringJava4sDAO dao;
	
	@RequestMapping("/get-cust-info")
	public List<Customer> customerInformation() {
		
		List<Customer> customers = dao.isData();		 
		
		return customers;
	}
}

