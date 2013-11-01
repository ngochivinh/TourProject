package com.software.tour.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.software.tour.domain.Restaurant;
import com.software.tour.service.RestaurantService;;


@Controller
public class RestaurantController {
	
final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private RestaurantService restaurantService;
	
	@RequestMapping(value = "/restaurant/add", method = RequestMethod.GET)
	//@RequestMapping(method = RequestMethod.GET)
	public ModelAndView Add(){
		ModelAndView modelAndView = new ModelAndView("restaurant/add");
		modelAndView.addObject("restaurant", new Restaurant());
		
		return modelAndView;
	}
}
