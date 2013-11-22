package com.software.tour.controller;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.software.tour.domain.Restaurant;
import com.software.tour.form.Message;
import com.software.tour.service.RestaurantService;
import com.software.tour.util.MyPageRequest;
import com.software.tour.util.UrlUtil;
import com.software.tour.web.form.RestaurantGrid;

@RequestMapping("/restaurants")
@Controller
public class RestaurantController {
	private static final Logger logger = LoggerFactory.getLogger(RestaurantController.class);
	private String searchTerm="";
	
	@Autowired
	private RestaurantService restaurantService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model uiModel){
		this.searchTerm = "";
		uiModel.addAttribute("searchTerm", searchTerm);
		return "restaurants/list";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET) 
	public String show(@PathVariable("id") Long id, Model uiModel) { 
		Restaurant restaurant = restaurantService.findById(id); 
		uiModel.addAttribute("restaurant", restaurant); 
		return "restaurants/show"; 
	}
	
	@RequestMapping(value = "/{id}", params = "delete", method = RequestMethod.GET) 
	public String delete(@PathVariable("id") Long id, Model uiModel,
			HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) { 
		if(restaurantService.findById(id)!=null) {
			restaurantService.delete(restaurantService.findById(id));
			uiModel.asMap().clear();
			if(restaurantService.findById(id)==null)
				uiModel.addAttribute("message", new Message("success", messageSource.getMessage("restaurant_delete_success", new Object[]{}, locale)));
			else
				uiModel.addAttribute("message", new Message("error", messageSource.getMessage("restaurant_delete_fail", new Object[]{}, locale)));
		}
		else
		{
			uiModel.addAttribute("message", new Message("error", messageSource.getMessage("restaurant_non_exist", new Object[]{}, locale)));
		}
		return "restaurants/list";
	}
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST) 
	public String update(Restaurant restaurant, BindingResult bindingResult, Model uiModel, 
			HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale,
			@RequestParam(value="file", required=false) Part file) { 
		logger.info("Updating contact"); 
		if (bindingResult.hasErrors()) { 
			uiModel.addAttribute("message",new Message("error",
					messageSource.getMessage("restaurant_save_fail", new Object[]{},locale)));
			uiModel.addAttribute("restaurant", restaurant); 
			return "restaurants/update";
		} 
		uiModel.asMap().clear(); 
		redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("restaurant_save_success", new Object[]{}, locale)));
		if (file != null) { 
			logger.info("File name: " + file.getName()); 
			logger.info("File size: " + file.getSize()); 
			logger.info("File content type: " + file.getContentType()); 
			byte[] fileContent = null; 
			try { 
				InputStream inputStream = file.getInputStream(); 
				if (inputStream == null) logger.info("File inputstream is null"); 
				fileContent = IOUtils.toByteArray(inputStream); 
				restaurant.setPhoto(fileContent); 
			} catch (IOException ex) { 
				logger.error("Error saving uploaded file"); 
			} 
				restaurant.setPhoto(fileContent); 
		}
		restaurantService.save(restaurant);
		return "redirect:/restaurants/" + UrlUtil.encodeUrlPathSegment(restaurant.getId().toString(), httpServletRequest); 
	}
	
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET) 
	public String updateForm(@PathVariable("id") Long id, Model uiModel) { 
		uiModel.addAttribute("restaurant", restaurantService.findById(id)); 
		return "restaurants/update"; 
	}
	
	@RequestMapping(params = "form", method=RequestMethod.POST)
	public String create(Restaurant restaurant, BindingResult bindingResult, Model uiModel,
			HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale,
			@RequestParam(value="file", required=false) Part file){
		logger.info("Create Restaurant");
		
		if(bindingResult.hasErrors()){
			uiModel.addAttribute("message",new Message("error",
					messageSource.getMessage("restaurant_save_fail", new Object[]{},locale)));
			uiModel.addAttribute("restaurant",restaurant);
			return "restaurants/create";
		}
		uiModel.asMap().clear();
		redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("restaurant_save_success", new Object[]{},locale)));
		// Process upload file 
		if (file != null) { 
			logger.info("File name: " + file.getName()); 
			logger.info("File size: " + file.getSize()); 
			logger.info("File content type: " + file.getContentType()); 
			byte[] fileContent = null; 
			try { 
				InputStream inputStream = file.getInputStream(); 
				if (inputStream == null) logger.info("File inputstream is null"); 
				fileContent = IOUtils.toByteArray(inputStream); 
				restaurant.setPhoto(fileContent); 
			} catch (IOException ex) { 
				logger.error("Error saving uploaded file"); 
			} 
				restaurant.setPhoto(fileContent); 
		}
		restaurantService.save(restaurant);
		//logger.info("Restaurant info: "+restaurant.getId()+" "+restaurant.getName()+" "+restaurant.getPhone()+" "+restaurant.getAddress()+" "+restaurant.getEmail()+" "+restaurant.getPrice()+" "+restaurant.getDescription());
		return "redirect:/restaurants/" + UrlUtil.encodeUrlPathSegment(restaurant.getId().toString(),httpServletRequest);
		
	}
	
	@RequestMapping(params="form",method=RequestMethod.GET)
	public String createForm(Model uiModel){
		logger.info("Start!");
		Restaurant restaurant = new Restaurant();
		uiModel.addAttribute("restaurant",restaurant);
		return "restaurants/create";
	}
	
	@RequestMapping(value = "/photo/{id}", method = RequestMethod.GET) 
	@ResponseBody 
	public byte[] downloadPhoto(@PathVariable("id") Long id) { 
		Restaurant restaurant = restaurantService.findById(id); 
		if (restaurant.getPhoto() != null) {
			logger.info("Downloading photo for id: {} with size: {}", restaurant.getId(), restaurant.getPhoto().length); 
		}
		return restaurant.getPhoto();
	}
	
	@RequestMapping(params="searchTerm", method = RequestMethod.POST) 
	public String search(@RequestParam(value="searchTerm", required=false) String searchTerm, Model uiModel) {
		this.searchTerm = searchTerm;
		uiModel.addAttribute("searchTerm", searchTerm);
		return "restaurants/list";
	}
	
	@RequestMapping(params="btnClear", method = RequestMethod.POST) 
	public String clearSearch(Model uiModel) {
		this.searchTerm = "";
		uiModel.addAttribute("searchTerm", searchTerm);
		return "restaurants/list";
	}
	
	@RequestMapping(value="/listgrid", method = RequestMethod.GET,
			produces="application/json")
	@ResponseBody
	public RestaurantGrid listGrid(@RequestParam(value="page",required=false)Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "sidx", required = false) String sortBy,
			@RequestParam(value = "sord", required = false) String order){
		
		logger.info("Listing tours for grid with page:{}, rows: {}",page,rows);
		logger.info("Listing tours for grid woth sort:{}, order: {}", sortBy, order);
		
		//Process order by
		Sort sort = null;
		String orderBy = sortBy;
		if(orderBy != null && orderBy.equals("phone"))
			orderBy = "phone";
		
		if(orderBy != null && order != null){
			if(order.equals("desc")){
				sort = new Sort(Sort.Direction.DESC, orderBy);
			}else
				sort = new Sort(Sort.Direction.ASC, orderBy);
		}
		
		//Construcs page request for current page
		//Note: page number for Spring Data JPA starts with 0, While jqGrid Starts with 1
		MyPageRequest myPageRequest = null;		
		if(sort!=null){
			myPageRequest = new MyPageRequest(page-1,rows,searchTerm,sort);
		}else{
			myPageRequest = new MyPageRequest(page-1,rows,searchTerm);
		}
		
		Page<Restaurant> restaurantPage = restaurantService.search(myPageRequest);
		
		//Construct the grid data that will return as JSON data
		RestaurantGrid restaurantGrid = new RestaurantGrid();
		
		restaurantGrid.setCurrentPage(restaurantPage.getNumber()+1);
		restaurantGrid.setTotalPages(restaurantPage.getTotalPages());
		restaurantGrid.setTotalRecords(restaurantPage.getTotalElements());
		
		restaurantGrid.setRestaurantData(Lists.newArrayList(restaurantPage.iterator()));
		
		return restaurantGrid;
	}
}
