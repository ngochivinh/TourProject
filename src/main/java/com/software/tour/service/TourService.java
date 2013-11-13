package com.software.tour.service;

import java.util.List;

import com.software.tour.domain.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TourService {
	public List<Tour> findAll();
	public Tour findById(Long id);
	public Tour save(Tour tour);
	//public void delete(@org.springframework.data.repository.query.Param("Id")Long id);
	public Page<Tour> findAllByPage(Pageable pageable);
}
