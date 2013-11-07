package com.software.tour.controller;

import com.software.tour.form.Message;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.software.tour.domain.Tour;
import com.software.tour.service.TourService;
import com.software.tour.util.*;

@RequestMapping("/tours")
@Controller
public class TourController {
	
	final Logger logger = LoggerFactory.getLogger(TourController.class);
	
	@Autowired
	private TourService tourService;
	
	@RequestMapping(method= RequestMethod.GET)
	public String list(Model uiModel){
		logger.info("Listing Tour");
		
		List<Tour> tours = tourService.findAll();
		uiModel.addAttribute("tours",tours);
		
		logger.info("No. of tours: "+tours.size());
		
		return "tours/list";
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id")Long id,Model uiModel){
		Tour tour = tourService.findById(id);
		uiModel.addAttribute("tour",tour);
		return "tours/show";
	}
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value = "/{id}", params = "form", method=RequestMethod.POST)
	public String update(Tour tour, BindingResult bindingResult, Model uiModel,
			HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale){
		logger.info("Update Tour");
		
		if(bindingResult.hasErrors()){
			uiModel.addAttribute("message",new Message("error",
					messageSource.getMessage("tour_save_fail", new Object[]{},locale)));
			uiModel.addAttribute(tour);
			return "tours/update";
		}
		uiModel.asMap().clear();
		redirectAttributes.addFlashAttribute("message", new Message("success",
				messageSource.getMessage("tour_save_success", new Object[]{},locale)));
		tourService.save(tour);
		return "redirect:/tours/" + UrlUtil.encodeUrlPathSegment(tour.getId().toString(),httpServletRequest);
		
	}
	
	@RequestMapping(value="/{id}",params="form",method=RequestMethod.GET)
	public String updateForm(@PathVariable("id")Long id,Model uiModel){
		logger.info("Start!");
		uiModel.addAttribute("tour", tourService.findById(id));
		return "tours/update";
	}
	
	
	@RequestMapping(params = "form", method=RequestMethod.POST)
	public String create(Tour tour, BindingResult bindingResult, Model uiModel,
			HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale){
		logger.info("Create Tour");
		
		if(bindingResult.hasErrors()){
			uiModel.addAttribute("message",new Message("error",
					messageSource.getMessage("tour_save_fail", new Object[]{},locale)));
			uiModel.addAttribute("tour",tour);
			return "tours/create";
		}
		uiModel.asMap().clear();
		redirectAttributes.addFlashAttribute("message", new Message("success",
				messageSource.getMessage("tour_save_success", new Object[]{},locale)));
		tourService.save(tour);
		return "redirect:/tours/" + UrlUtil.encodeUrlPathSegment(tour.getId().toString(),httpServletRequest);
		
	}
	
	@RequestMapping(params="form",method=RequestMethod.GET)
	public String createForm(Model uiModel){
		logger.info("Start!");
		Tour tour = new Tour();
		uiModel.addAttribute("tour",tour);
		return "tours/create";
	}
	
}
