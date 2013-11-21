package com.software.tour.service;

import java.util.List;

import com.software.tour.domain.Tour;
import com.software.tour.util.SearchDTO;
import com.software.tour.util.MyPageRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TourService {
	public List<Tour> findAll();
	public Tour findById(Long id);
	public Tour save(Tour tour);
	public void delete(Tour tour);
	public Page<Tour> findAllByPage(Pageable pageable);
	public List<Tour> search(SearchDTO dto);
	//public List<Tour>findAllForPage(int pageIndex, int pageSize);
	public Page<Tour> search(MyPageRequest pageable);
}
