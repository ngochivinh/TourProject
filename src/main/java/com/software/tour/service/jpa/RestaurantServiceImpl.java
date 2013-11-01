package com.software.tour.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import com.software.tour.domain.Restaurant;
import com.software.tour.repository.RestaurantRepository;
import com.software.tour.service.RestaurantService;
import com.google.common.collect.Lists;

@Service("restaurantService")
@Repository
@Transactional
public class RestaurantServiceImpl implements RestaurantService {
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	public List<Restaurant> findAll(){
		return Lists.newArrayList(restaurantRepository.findAll());
		
	}
	
	public void Create(Restaurant restaurant){
		restaurantRepository.save(restaurant);
	}
}
