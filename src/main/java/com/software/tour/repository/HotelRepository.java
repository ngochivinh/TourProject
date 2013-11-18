package com.software.tour.repository;

import com.software.tour.domain.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HotelRepository extends PagingAndSortingRepository<Hotel, Long>, JpaRepository<Hotel, Long>, JpaSpecificationExecutor<Hotel> {
	
}