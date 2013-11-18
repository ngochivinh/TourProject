package com.software.tour.service;

import java.util.List;

import com.software.tour.domain.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HotelService {
	public List<Hotel> findAll();
	public Hotel findById(Long id);
	public Hotel save(Hotel hotel);
	public void delete(Hotel hotel);
	public Page<Hotel> findAllByPage(Pageable pageable);
}
