package com.software.tour.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Override
	public List<Restaurant> findAll() {
		return Lists.newArrayList(restaurantRepository.findAll());
	}

	@Override
	public Restaurant findById(Long id) {
		return restaurantRepository.findOne(id);
	}

	@Override
	public Restaurant save(Restaurant restaurent) {
		return restaurantRepository.save(restaurent);
	}

	@Override
	public void delete(Restaurant restaurent) {
		restaurantRepository.delete(restaurent);
	}

	@Override
	public Page<Restaurant> findAllByPage(Pageable pageable) {
		return restaurantRepository.findAll(pageable);
	}
	
	
}
