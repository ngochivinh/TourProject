package com.software.tour.repository;
import com.software.tour.domain.Hotel;


import org.springframework.data.repository.PagingAndSortingRepository;

public interface HotelRepository extends PagingAndSortingRepository<Hotel, Long> {
	
}