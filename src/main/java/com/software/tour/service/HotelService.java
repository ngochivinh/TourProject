package com.software.tour.service;

import java.util.List;

import com.software.tour.domain.Hotel;
import com.software.tour.service.jpa.MyPageRequest;
import com.software.tour.service.jpa.SearchDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HotelService {
	public List<Hotel> findAll();
	//public List<Hotel> findAllForPage(int pageIndex, int pageSize);
	public Hotel findById(Long id);
	public Hotel save(Hotel hotel);
	public void delete(Hotel hotel);
	public Page<Hotel> findAllByPage(Pageable pageable);
	public List<Hotel> search(SearchDTO dto);
	public Page<Hotel> search(MyPageRequest pageable);
}
