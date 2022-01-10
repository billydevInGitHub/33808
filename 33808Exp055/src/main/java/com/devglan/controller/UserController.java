package com.devglan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.devglan.model.UserDetails;
import com.devglan.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ModelAndView userDetails() {
		ModelAndView modelAndView = new ModelAndView();
		List<UserDetails> userDetails = userService.getUserDetails();
		modelAndView.addObject("users", userDetails);
		modelAndView.setViewName("userDetails");
		return modelAndView;
	}

//	@GetMapping("/report")
//	public String handleForexRequest(Model model) {
////		model.addAttribute("report", getReport());
//		return "reportView";
//	}

	@RequestMapping(value = "/detailpdf", method = RequestMethod.GET)
	public ModelAndView userDetailsPDF() {

		List<UserDetails> userDetails = userService.getUserDetails();

		return new ModelAndView("pdfView", "userList",  userDetails);
	}

}
