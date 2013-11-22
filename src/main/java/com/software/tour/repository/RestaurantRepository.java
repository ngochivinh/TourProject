package com.software.tour.repository;

import com.software.tour.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RestaurantRepository extends PagingAndSortingRepository<Restaurant, Long>, JpaRepository<Restaurant, Long>, JpaSpecificationExecutor<Restaurant> {
	
}