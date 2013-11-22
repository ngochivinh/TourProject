package com.software.tour.controller;

import com.google.common.collect.Lists;
import com.software.tour.form.Message;

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

import com.software.tour.domain.Hotel;
import com.software.tour.service.HotelService;
import com.software.tour.util.MyPageRequest;
import com.software.tour.util.UrlUtil;
import com.software.tour.web.form.HotelGrid;

@RequestMapping("/hotels")
@Controller
public class HotelController {
	private static final Logger logger = LoggerFactory.getLogger(HotelController.class);
	private String searchTerm="";
	
	@Autowired
	private HotelService hotelService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model uiModel){
		/*logger.info("Listing hotels");
		List<Hotel> hotels = hotelService.findAll();
		uiModel.addAttribute("hotels", hotels);
		logger.info("No. of hotel:" + hotels.size());*/
		this.searchTerm = "";
		uiModel.addAttribute("searchTerm", searchTerm);
		return "hotels/list";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET) 
	public String show(@PathVariable("id") Long id, Model uiModel) { 
		Hotel hotel = hotelService.findById(id); 
		uiModel.addAttribute("hotel", hotel); 
		return "hotels/show"; 
	}
	
	@RequestMapping(value = "/{id}", params = "delete", method = RequestMethod.GET) 
	public String delete(@PathVariable("id") Long id, Model uiModel,
			HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
		uiModel.asMap().clear();
		if(hotelService.findById(id)!=null) {
			hotelService.delete(hotelService.findById(id));
			if(hotelService.findById(id)==null)
				uiModel.addAttribute("message", new Message("success", messageSource.getMessage("hotel_delete_success", new Object[]{}, locale)));
			else
				uiModel.addAttribute("message", new Message("error", messageSource.getMessage("hotel_delete_fail", new Object[]{}, locale)));
		}
		else {
			uiModel.addAttribute("message", new Message("error", messageSource.getMessage("hotel_non_exist", new Object[]{}, locale)));
		}
		return "hotels/list";
	}
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST) 
	public String update(Hotel hotel, BindingResult bindingResult, Model uiModel, 
			HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale,
			@RequestParam(value="file", required=false) Part file) { 
		logger.info("Updating hotel"); 
		if (bindingResult.hasErrors()) { 
			uiModel.addAttribute("message",new Message("error",
					messageSource.getMessage("hotel_save_fail", new Object[]{},locale)));
			uiModel.addAttribute("hotel", hotel); 
			return "hotels/update";
		} 
		uiModel.asMap().clear(); 
		redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("hotel_save_success", new Object[]{}, locale)));
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
				hotel.setPhoto(fileContent); 
			} catch (IOException ex) { 
				logger.error("Error saving uploaded file"); 
			} 
				hotel.setPhoto(fileContent); 
		}
		hotelService.save(hotel);
		return "redirect:/hotels/" + UrlUtil.encodeUrlPathSegment(hotel.getId().toString(), httpServletRequest); 
	}
	
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET) 
	public String updateForm(@PathVariable("id") Long id, Model uiModel) { 
		uiModel.addAttribute("hotel", hotelService.findById(id)); 
		return "hotels/update"; 
	}
	
	@RequestMapping(params = "form", method=RequestMethod.POST)
	public String create(Hotel hotel, BindingResult bindingResult, Model uiModel,
			HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale,
			@RequestParam(value="file", required=false) Part file){
		logger.info("Create Hotel");
		
		if(bindingResult.hasErrors()){
			uiModel.addAttribute("message",new Message("error",
					messageSource.getMessage("hotel_save_fail", new Object[]{},locale)));
			uiModel.addAttribute("hotel",hotel);
			return "hotels/create";
		}
		uiModel.asMap().clear();
		redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("hotel_save_success", new Object[]{},locale)));
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
				hotel.setPhoto(fileContent); 
			} catch (IOException ex) { 
				logger.error("Error saving uploaded file"); 
			} 
				hotel.setPhoto(fileContent); 
		}
		hotelService.save(hotel);
		//logger.info("Hotel info: "+hotel.getId()+" "+hotel.getName()+" "+hotel.getPhone()+" "+hotel.getAddress()+" "+hotel.getEmail()+" "+hotel.getPrice()+" "+hotel.getDescription());
		return "redirect:/hotels/" + UrlUtil.encodeUrlPathSegment(hotel.getId().toString(),httpServletRequest);
		
	}
	
	@RequestMapping(params="form",method=RequestMethod.GET)
	public String createForm(Model uiModel){
		logger.info("Start!");
		Hotel hotel = new Hotel();
		uiModel.addAttribute("hotel",hotel);
		return "hotels/create";
	}
	
	@RequestMapping(value = "/photo/{id}", method = RequestMethod.GET) 
	@ResponseBody 
	public byte[] downloadPhoto(@PathVariable("id") Long id) { 
		Hotel hotel = hotelService.findById(id); 
		if (hotel.getPhoto() != null) {
			logger.info("Downloading photo for id: {} with size: {}", hotel.getId(), hotel.getPhoto().length); 
		}
		return hotel.getPhoto();
	}
	
	@RequestMapping(params="searchTerm", method = RequestMethod.POST) 
	public String search(@RequestParam(value="searchTerm", required=false) String searchTerm, Model uiModel) {
		this.searchTerm = searchTerm;
		uiModel.addAttribute("searchTerm", searchTerm);
		return "hotels/list";
	}
	
	@RequestMapping(params="btnClear", method = RequestMethod.POST) 
	public String clearSearch(Model uiModel) {
		this.searchTerm = "";
		uiModel.addAttribute("searchTerm", searchTerm);
		return "hotels/list";
	}
	
	@RequestMapping(value="/listgrid", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public HotelGrid listGrid(@RequestParam(value="page",required=false)Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "sidx", required = false) String sortBy,
			@RequestParam(value = "sord", required = false) String order){//, @PathVariable("str") String str
		
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
		//PageRequest pageRequest = null;
		MyPageRequest myPageRequest = null;
		
		if(sort!=null){
			//pageRequest = new PageRequest(page-1,rows,sort);
			myPageRequest = new MyPageRequest(page-1,rows,searchTerm,sort);
		}else{
			//pageRequest = new PageRequest(page-1,rows);
			myPageRequest = new MyPageRequest(page-1,rows,searchTerm);
		}
		
		//Page<Hotel> hotelPage = hotelService.findAllByPage(pageRequest);
		Page<Hotel> hotelPage = hotelService.search(myPageRequest);
		
		//Construct the grid data that will return as JSON data
		HotelGrid hotelGrid = new HotelGrid();
		
		hotelGrid.setCurrentPage(hotelPage.getNumber()+1);
		hotelGrid.setTotalPages(hotelPage.getTotalPages());
		hotelGrid.setTotalRecords(hotelPage.getTotalElements());
		
		hotelGrid.setHotelData(Lists.newArrayList(hotelPage.iterator()));
		
		return hotelGrid;
	}
}
