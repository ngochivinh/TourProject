package com.software.tour.service;

import java.util.List;
import com.software.tour.domain.Tour;

public interface TourService {
	public List<Tour> findAll();
	public Tour findById(Long id);
	public Tour save(Tour tour);
}
