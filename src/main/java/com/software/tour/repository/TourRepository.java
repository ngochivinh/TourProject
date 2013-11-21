package com.software.tour.repository;
import com.software.tour.domain.Tour;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.software.tour.domain.Tour;

public interface TourRepository extends PagingAndSortingRepository<Tour, Long>, JpaRepository<Tour, Long>, JpaSpecificationExecutor<Tour> {
	
}
