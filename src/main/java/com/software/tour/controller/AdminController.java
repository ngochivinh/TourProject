package com.software.tour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/admin")
@Controller
public class AdminController {
	
	@RequestMapping(method= RequestMethod.GET)
	public String Admin(){
		return "admin/login";
	}
}
