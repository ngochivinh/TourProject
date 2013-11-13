package com.software.tour.repository;
import com.software.tour.domain.Tour;


import org.springframework.data.repository.PagingAndSortingRepository;
import com.software.tour.domain.Tour;

public interface TourRepository extends PagingAndSortingRepository<Tour, Long> {
	
}
