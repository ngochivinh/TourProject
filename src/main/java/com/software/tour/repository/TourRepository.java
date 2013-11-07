package com.software.tour.repository;

import org.springframework.data.repository.CrudRepository;
import com.software.tour.domain.Tour;

public interface TourRepository extends CrudRepository<Tour, Long> {
	
}
