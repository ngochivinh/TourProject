package com.software.tour.controller;

import com.google.common.collect.Lists;
import com.software.tour.form.Message;
import com.software.tour.web.form.TourGrid;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@PreAuthorize("isAuthenticated()")
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
			HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale,
			@RequestParam(value="file", required=false)Part file){
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
		
		//process upload File
		if(file!=null){
			logger.info("File name: "+file.getName());
			logger.info("File size: "+file.getSize());
			logger.info("File content type: "+ file.getContentType());
			byte[] fileContent = null;
			try{
				InputStream inputStream = file.getInputStream();
				if(inputStream==null) logger.info("File inputstream is null");
				fileContent=IOUtils.toByteArray(inputStream);
				tour.setPhoto(fileContent);
			}catch(IOException ex){
				logger.info("Error saving upload file");
			}
			tour.setPhoto(fileContent);
		}
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
			HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale,
			@RequestParam(value="file", required=false)Part file){
		
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
		
		//process upload File
		if(file!=null){
			logger.info("File name: "+file.getName());
			logger.info("File size: "+file.getSize());
			logger.info("File content type: "+ file.getContentType());
			byte[] fileContent = null;
			try{
				InputStream inputStream = file.getInputStream();
				if(inputStream==null) logger.info("File inputstream is null");
				fileContent=IOUtils.toByteArray(inputStream);
				tour.setPhoto(fileContent);
			}catch(IOException ex){
				logger.info("Error saving upload file");
			}
			tour.setPhoto(fileContent);
		}
		
		tourService.save(tour);
		
		return "redirect:/tours/" +
			UrlUtil.encodeUrlPathSegment(tour.getId().toString(),httpServletRequest);
	}
	
	
	@RequestMapping(value = "/photo/{id}", method = RequestMethod.GET)
	@ResponseBody
	public byte[] downloadPhoto(@PathVariable("id")Long id){
		
		Tour tour =  tourService.findById(id);
		
		if(tour.getPhoto()!=null){
			logger.info("Downloading photo for id: {} with size: {}",tour.getId(),tour.getPhoto().length);
		}
		
		return tour.getPhoto();
	}
	
	
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(params="form",method=RequestMethod.GET)
	public String createForm(Model uiModel){
		logger.info("Start!");
		Tour tour = new Tour();
		uiModel.addAttribute("tour",tour);
		return "tours/create";
	}
	
	
	
	
	
	
	@RequestMapping(value="/{id}",params="delete",method=RequestMethod.GET)
	public String delete(@PathVariable("id")Long id,Model uiModel, RedirectAttributes redirectAttributes, Locale locale){
		
		if(tourService.findById(id)!=null){
			tourService.delete(tourService.findById(id));
			if(tourService.findById(id)==null){
				uiModel.addAttribute("message",new Message("success",
						messageSource.getMessage("tour_save_success", new Object[]{},locale)));
			}else{
				uiModel.addAttribute("message",new Message("error",
						messageSource.getMessage("tour_save_fail", new Object[]{},locale)));
			}	
		}else{
			uiModel.addAttribute("message",new Message("error",
					messageSource.getMessage("tour_save_fail", new Object[]{},locale)));
		}
		return "tours/list";
		
	}
	
	
	
	
	@RequestMapping(value="/listgrid", method = RequestMethod.GET,
			produces="application/json")
	@ResponseBody
	public TourGrid listGrid(@RequestParam(value="page",required=false)Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "sidx", required = false) String sortBy,
			@RequestParam(value = "sord", required = false) String order){
		
		logger.info("Listing tours for grid with page:{}, rows: {}",page,rows);
		logger.info("Listing tours for grid woth sort:{}, order: {}", sortBy, order);
		
		//Process order by
		Sort sort = null;
		String orderBy = sortBy;
		if(orderBy != null && orderBy.equals("startDate"))
			orderBy = "startDate";
		
		if(orderBy != null && order != null){
			if(order.equals("desc")){
				sort = new Sort(Sort.Direction.DESC, orderBy);
			}else
				sort = new Sort(Sort.Direction.ASC, orderBy);
		}
		
		//Construcs page request for current page
		//Note: page number for Spring Data JPA starts with 0, While jqGrid Starts with 1
		PageRequest pageRequest = null;
		
		if(sort!=null){
			pageRequest = new PageRequest(page-1,rows,sort);
		}else{
			pageRequest = new PageRequest(page-1,rows);
		}
		
		Page<Tour> tourPage = tourService.findAllByPage(pageRequest);
		
		//Construct the grid data that will return as JSON data
		TourGrid tourGrid = new TourGrid();
		
		tourGrid.setCurrentPage(tourPage.getNumber()+1);
		tourGrid.setTotalPages(tourPage.getTotalPages());
		tourGrid.setTotalRecords(tourPage.getTotalElements());
		
		tourGrid.setTourData(Lists.newArrayList(tourPage.iterator()));
		
		return tourGrid;
	}
	
}

