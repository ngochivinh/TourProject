package com.software.tour.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.software.tour.domain.User;
import com.software.tour.service.UserService;


@Controller
public class UserController {
	final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/users/list", method = RequestMethod.GET)
	//@RequestMapping(method = RequestMethod.GET)
	public String list(Model uiModel){
		logger.info("Listing contacts");
		
		List<User> users = userService.findAll();
		
		uiModel.addAttribute("users", users);
		
		logger.info("No. of user:" + users.size());
		return "users/list";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login() {
	    return "users/login";
	}
	
}
