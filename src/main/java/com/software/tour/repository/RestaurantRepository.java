package com.software.tour.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.software.tour.domain.Restaurant;

public interface RestaurantRepository extends PagingAndSortingRepository<Restaurant, Long> {

}
