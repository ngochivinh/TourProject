package com.software.tour.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.software.tour.domain.Tour;
import com.software.tour.service.TourService;
import com.software.tour.repository.TourRepository;
import com.google.common.collect.Lists;

@Service("tourService")
@Repository
@Transactional
public class TourServiceImpl implements TourService {
	
	@Autowired
	private TourRepository tourRepository;
	
	@Transactional(readOnly=true)
	public List<Tour> findAll(){
		return Lists.newArrayList(tourRepository.findAll());
	}
	
	@Transactional(readOnly=true)
	public Tour findById(Long id){
		return tourRepository.findOne(id);
	}
	
	public Tour save(Tour tour) {
		return tourRepository.save(tour);
	}
	
}
