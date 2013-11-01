package com.software.tour.repository;

import org.springframework.data.repository.CrudRepository;
import com.software.tour.domain.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

}
