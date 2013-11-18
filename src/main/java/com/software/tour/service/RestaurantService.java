package com.software.tour.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.software.tour.domain.Restaurant;

public interface RestaurantService {
	
	public List<Restaurant> findAll();
	public Restaurant findById(Long id);
	public Restaurant save(Restaurant restaurent);
	public void delete(Restaurant restaurent);
	public Page<Restaurant> findAllByPage(Pageable pageable);
}
